package com.code.monotor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Java Monitor Server
 * @author codedorado
 */
@SpringBootApplication
@MapperScan("com.code.monotor.mapper")
public class JMServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JMServerApplication.class, args);
    }

}
