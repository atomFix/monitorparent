package com.code.monotor;

import com.code.monitor.cronjob.controller.ThreadPoolController;
import com.code.monitor.properties.PropertiesConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * 对 jmonitor 的自动配置相关 module
 *
 * @author 86178
 */
@Configuration
@ConditionalOnClass(ThreadPoolController.class)
public class JMonitorAutoConfiguration {

    @Value(" ${mq.host}")
    private String host;
    @Value("${mq.ip}")
    private String ip;

    @Bean
    @ConditionalOnMissingBean(ThreadPoolController.class)
    @ConditionalOnProperty(name = "jmonitor.enable", havingValue = "true")
    public ThreadPoolController threadPoolController() {
        System.out.println(host);
        System.out.println(ip);
        if (!StringUtils.hasLength(host)) {
            host = "127.0.0.1";
        }
        if (!StringUtils.hasLength(ip)) {
            ip = "5672";
        }
        PropertiesConfig.configMap.put("mq.host", host);
        PropertiesConfig.configMap.put("mq.ip", ip);
        return new ThreadPoolController();
    }

}
