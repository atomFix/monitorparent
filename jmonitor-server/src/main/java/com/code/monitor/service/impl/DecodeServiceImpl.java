package com.code.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.code.monitor.constant.Constant;
import com.code.monitor.entity.ApplicationEntity;
import com.code.monitor.entity.JStatGCEntity;
import com.code.monitor.entity.JStatMemoryEntity;
import com.code.monitor.service.ApplicationEntityService;
import com.code.monitor.service.DecodeService;
import com.code.monitor.service.JStatGCEntityService;
import com.code.monitor.service.JStatMemoryEntityService;
import com.code.monitor.utils.KVEntity;
import com.code.monitor.utils.MapperUtils;
import com.google.common.base.CharMatcher;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/20 18:48
 */
@Service
@Slf4j
public class DecodeServiceImpl implements DecodeService {


    /**
     * 真实存储数据的结构 key
     */
    private static final String DATA = "Data";
    @Autowired
    ApplicationEntityService applicationEntityService;
    @Autowired
    JStatMemoryEntityService jStatMemoryEntityService;
    @Autowired
    JStatGCEntityService jStatGCEntityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void restore(String message) {
        JSONObject x = JSON.parseObject(message);
        finalStoreB(x);
    }

    /**
     * 解析数据
     *
     * @param x 具体数据
     */
    private void finalStoreB(JSONObject x) {
        // 用以 restore 的对象
        AtomicReference<JStatGCEntity> jStatGCEntity = new AtomicReference<>();
        AtomicReference<JStatMemoryEntity> jStatMemoryEntity = new AtomicReference<>();
        AtomicReference<ApplicationEntity> applicationEntity = new AtomicReference<>();
        AtomicBoolean flag = new AtomicBoolean(true);
        StringBuilder appId = new StringBuilder();
        AtomicReference<LocalDateTime> localDate = new AtomicReference<>();

        // 得到 data 里面的数据
        Set<Map.Entry<String, Object>> entries = x.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            JSONObject messageData = JSON.parseObject(JSON.toJSONString(entry.getValue()));
            // 配置基本信息，app_id & datetime
            if (flag.get()) {
                messageData.forEach((k, v) -> {
                    setBasicMessage(flag, appId, localDate, k, v);
                });
            }
            messageData.forEach((k, v) -> {
                // 解析三种数据里面具体的内容
                if (!key.equals(Constant.J_INFO) && k.equals(DATA)) {
                    String s1 = JSON.toJSONString(v);
                    List<KVEntity> kvEntities = JSON.parseArray(s1, KVEntity.class);
                    // save
                    if (Constant.J_STAT_MEMORY.equals(key)) {
                        jStatMemoryEntity.set(MapperUtils.saveStatMemory(appId.toString(), localDate.get(), kvEntities));
                    }
                    if (Constant.J_STAT_GC.equals(key)) {
                        jStatGCEntity.set(MapperUtils.saveStatGC(appId.toString(), localDate.get(), kvEntities));
                    }
                } else if (k.equals(DATA)) {
                    List<KVEntity> paraList = getTrueParams(v);
                    applicationEntity.set(MapperUtils.saveApplicationInfo(appId.toString(), localDate.get(), paraList));
                }
            });
        }
        // Gauge jobQueueSize = Gauge.build()
        //         .name("jmonitor")
        //         .help("Current number of jobs waiting in queue")
        //         .register();
        // jobQueueSize.set(Double.parseDouble(jStatGCEntity.get().getEC()));
        // 数据持久化
        trueStore(jStatGCEntity, jStatMemoryEntity, applicationEntity);
        log.info("{} : {}", appId, localDate.get());
    }

    /**
     * save
     *
     * @param jStatGCEntity     jStatGCEntity
     * @param jStatMemoryEntity jStatMemoryEntity
     * @param applicationEntity applicationEntity
     */
    private void trueStore(AtomicReference<JStatGCEntity> jStatGCEntity, AtomicReference<JStatMemoryEntity> jStatMemoryEntity, AtomicReference<ApplicationEntity> applicationEntity) {
        jStatGCEntityService.save(jStatGCEntity.get());
        applicationEntityService.save(applicationEntity.get());
        jStatMemoryEntityService.save(jStatMemoryEntity.get());
    }

    /**
     * 获得处理好的数据
     *
     * @param v 具体的数据 data
     * @return 信息集合
     */
    private List<KVEntity> getTrueParams(Object v) {
        final String[] params = new String[2];
        AtomicInteger index = new AtomicInteger(0);

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(v));
        List<String> infoList = new ArrayList<>(16);
        jsonObject.forEach((k1, v1) -> {
            params[index.getAndIncrement()] = getInfoList(infoList, v1.toString());
        });
        List<KVEntity> kvCollections = infoList.stream().distinct()
                .map(str -> str.substring(str.indexOf(':') + 1))
                .map(str -> {
                    String[] split = str.split("=");
                    return new KVEntity(split[0], String.valueOf(Double.parseDouble(split[1]) / 1024 / 1024));
                }).collect(Collectors.toList());
        // 获取所有参数信息
        kvCollections.add(new KVEntity("params", params[0] + params[1]));
        return kvCollections;
    }

    /**
     * 设置基本的信息，appId & date
     *
     * @param flag      flag
     * @param appId     appId
     * @param localDate localDate
     * @param k         key
     * @param v         value
     */
    private void setBasicMessage(AtomicBoolean flag, StringBuilder appId, AtomicReference<LocalDateTime> localDate, String k, Object v) {
        if (Constant.SERVER_NAME.equals(k)) {
            appId.append(v);
        } else if (Constant.DATA_TIME.equals(k)) {
            String s1 = JSON.toJSONString(v);
            localDate.set(LocalDateTime.parse(s1.substring(1, s1.length() - 1)));
        }
        if (appId.length() != 0 && !Objects.equal(localDate.get(), null)) {
            flag.set(false);
        }
    }

    /**
     * 得到需要保存的 JVM 参数
     *
     * @param infoList 解析后数据的存储集合
     * @param infos    要解析的数据
     */
    public String getInfoList(List<String> infoList, String infos) {
        ArrayList<String> strings = Lists.newArrayList(Splitter.on("\",\"").omitEmptyStrings()
                .trimResults(CharMatcher.is('"'))
                .split(infos.substring(1, infos.length() - 1)));
        List<String> collect = strings.stream()
                .filter(str -> str.contains("-XX:") && str.contains("Size="))
                .distinct().collect(Collectors.toList());
        infoList.addAll(collect);
        return String.join("<br>", strings);
    }
}
