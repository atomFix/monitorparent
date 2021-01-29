package com.code.monitor;

import com.code.monitor.mq.rabbit.impl.RabbitServiceImpl;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        Class.forName("com.code.monitor.cronjob.controller.ThreadPoolControl");
    }

    @Test
    public void teste() throws Exception {
    }

    @Test
    public void sendMessage() throws ClassNotFoundException {
        Class.forName("com.code.monitor.mq.rabbit.config.RabbitConfig");
        RabbitServiceImpl rabbitTemplate = RabbitServiceImpl.RABBIT_TEMPLATE;
        IntStream.rangeClosed(1, 10).forEach(num -> rabbitTemplate.send("hello world " + num));

    }


}
