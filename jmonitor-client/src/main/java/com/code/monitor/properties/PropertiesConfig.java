package com.code.monitor.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/27 10:14
 */
public class PropertiesConfig {

    public static HashMap<String, String> configMap = new HashMap<>(32);

    static {
        initProperties();
    }

    public static void initProperties() {
        try (InputStream inputStream = PropertiesConfig.class.getClassLoader().getResourceAsStream("monitor.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            properties.forEach((k, v) -> {
                if (!configMap.containsKey(k)) {
                    configMap.put(String.valueOf(k), String.valueOf(v));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
