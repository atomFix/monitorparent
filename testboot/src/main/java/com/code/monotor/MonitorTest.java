package com.code.monotor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 * @author 86178
 */
@SpringBootApplication
public class MonitorTest {
    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(MonitorTest.class, args);
        Class.forName("com.code.monitor.cronjob.controller.ThreadPoolControl");
    }
}
