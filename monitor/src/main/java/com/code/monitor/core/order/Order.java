package com.code.monitor.core.order;

import com.code.monitor.properties.PropertiesConfig;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/26 20:40
 */
public interface Order {

    String SERVER_NAME = PropertiesConfig.configMap.getOrDefault("monitor.server.name", UUID.randomUUID().toString().substring(0, 8));

    /**
     * 获取 T 对象的信息
     *
     * @return T
     * @throws Exception 可预测异常
     */
    Object getInfo() throws Exception;

    String getOrderName();

    default Map<String, Object> getObject() throws Exception {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        HashMap<String, Object> map = new HashMap<>();
        map.put("DateTime", LocalDateTime.now().toString());
        map.put("ServerName", hostAddress + ":" + SERVER_NAME);
        map.put("Data", getInfo());
        return map;
    }

}
