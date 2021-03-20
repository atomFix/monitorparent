package com.code.monotor.mq.serivce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.code.monotor.service.RestoreService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/2/17 20:34
 */
@Component
public class MessageConsumer {

    @Autowired
    RestoreService restoreService;

    // TODO: 记得打开
    @RabbitListener(queues = "data_queue")
    public void process(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            restoreService.restore(message);
            channel.basicAck(tag, false);
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException | IOException e) {
            channel.basicNack(tag, false, true);
        }
    }

}
