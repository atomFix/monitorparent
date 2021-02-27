package com.code.monotor.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/2/17 15:30
 */
@Configuration
public class DruidConfig {
    //绑定到yml
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * http://localhost:8081/druid 进入
     * 后台监控
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> a() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要人登录，账号密码设置
        HashMap<String, String> hashMap = new HashMap<>();
        //增加配置
        //登录key 固定的loginUsername
        hashMap.put("loginUsername", "admin");
        //登录密码  固定loginPassword
        hashMap.put("loginPassword", "123456");

        //允许谁可以访问
        hashMap.put("allow", "127.0.0.1");
        //初始化参数
        bean.setInitParameters(hashMap);
        return bean;
    }

}
