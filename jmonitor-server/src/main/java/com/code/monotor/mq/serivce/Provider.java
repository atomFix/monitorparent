package com.code.monotor.mq.serivce;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/2/18 16:30
 */
@Component
public class Provider {
    @Autowired
    AmqpTemplate rabbitTemplate;

    public static final String QUEUENAME = "chatbot";

    public void send(String str) {
        rabbitTemplate.convertAndSend(str);
    }
}
