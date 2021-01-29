package com.code.monitor.core.order;

import com.alibaba.fastjson.JSON;
import com.code.monitor.properties.PropertiesConfig;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/26 20:40
 */
public interface Order {

    String serverName = PropertiesConfig.configMap.getOrDefault("server.name", UUID.randomUUID().toString().substring(0, 8));

    /**
     * 获取 T 对象的信息
     *
     * @return T
     * @throws Exception 可预测异常
     */
    Object getInfo() throws Exception;

    String getOrderName();

    default Map<String, Object> getObject() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("DateTime", LocalDate.now().toString());
        map.put("ServerName", serverName);
        map.put("Data", getInfo());
        return map;
    }

}
