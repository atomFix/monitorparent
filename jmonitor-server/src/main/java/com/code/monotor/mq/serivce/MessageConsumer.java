package com.code.monotor.mq.serivce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/2/17 20:34
 */
@Component
public class MessageConsumer {

    // TODO: 记得打开
    // @RabbitListener(queues = "data_queue")
    public void process(String str) {
        JSONObject x = JSON.parseObject(str);
        x.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
