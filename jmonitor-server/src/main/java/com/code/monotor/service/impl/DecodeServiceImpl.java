package com.code.monotor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.code.monotor.constant.Constant;
import com.code.monotor.entity.ApplicationEntity;
import com.code.monotor.entity.JStatGCEntity;
import com.code.monotor.entity.JStatMemoryEntity;
import com.code.monotor.service.ApplicationEntityService;
import com.code.monotor.service.DecodeService;
import com.code.monotor.service.JStatGCEntityService;
import com.code.monotor.service.JStatMemoryEntityService;
import com.code.monotor.utils.KVEntity;
import com.code.monotor.utils.MapperUtils;
import com.google.common.base.CharMatcher;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
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

    @Autowired
    ApplicationEntityService applicationEntityService;
    @Autowired
    JStatMemoryEntityService jStatMemoryEntityService;
    @Autowired
    JStatGCEntityService jStatGCEntityService;

    /**
     * 真实存储数据的结构 key
     */
    private static final String DATA = "Data";

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
            // TODO: 没做完
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
        // 数据持久化
        trueStore(jStatGCEntity, jStatMemoryEntity, applicationEntity);
        log.info("{} : {}", appId, localDate.get());
    }

    /**
     * save
     * @param jStatGCEntity
     * @param jStatMemoryEntity
     * @param applicationEntity
     */
    private void trueStore(AtomicReference<JStatGCEntity> jStatGCEntity, AtomicReference<JStatMemoryEntity> jStatMemoryEntity, AtomicReference<ApplicationEntity> applicationEntity) {
        jStatGCEntityService.save(jStatGCEntity.get());
        applicationEntityService.save(applicationEntity.get());
        jStatMemoryEntityService.save(jStatMemoryEntity.get());
    }

    /**
     * 获得处理好的数据
     *
     * @param v
     * @return
     */
    private List<KVEntity> getTrueParams(Object v) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(v));
        List<String> infoList = new ArrayList<>(16);
        jsonObject.forEach((k1, v1) -> {
            getInfoList(infoList, v1.toString());
        });
        return infoList.stream().distinct()
                .map(str -> str.substring(str.indexOf(':') + 1))
                .map(str -> {
                    String[] split = str.split("=");
                    return new KVEntity(split[0], String.valueOf(Double.parseDouble(split[1]) / 1024 / 1024));
                }).collect(Collectors.toList());
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
     * @param infoList
     * @param infos
     * @return
     */
    public void getInfoList(List<String> infoList, String infos) {
        ArrayList<String> strings = Lists.newArrayList(Splitter.on("\",\"").omitEmptyStrings()
                .trimResults(CharMatcher.is('"'))
                .split(infos.substring(1, infos.length() - 1)));
        List<String> collect = strings.stream()
                .filter(str -> str.contains("-XX:") && str.contains("Size="))
                .distinct().collect(Collectors.toList());
        infoList.addAll(collect);
    }
}
