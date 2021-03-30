package com.code.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Java Monitor Server
 *
 * @author codedorado
 */
@SpringBootApplication
@EnableScheduling
public class JMServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JMServerApplication.class, args);
    }
}
