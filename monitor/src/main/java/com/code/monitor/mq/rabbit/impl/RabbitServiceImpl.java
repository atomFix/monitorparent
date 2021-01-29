package com.code.monitor.mq.rabbit.impl;

import com.code.monitor.mq.MessageQueueService;
import com.code.monitor.mq.rabbit.config.RabbitConfig;
import com.code.monitor.mq.rabbit.constant.RabbitConstant;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/28 21:17
 */
public class RabbitServiceImpl implements MessageQueueService {

    public static final RabbitServiceImpl RABBIT_TEMPLATE = new RabbitServiceImpl();

    private RabbitServiceImpl() {
        super();
    }

    @Override
    public void send(String msg) {
        RabbitConfig.ChannelEntry channelEntry = RabbitConfig.rabbitConfig.getChannel();
        Channel channel = channelEntry.getChannel();
        //发送消息使用的routing-key
        String routing_key = "data_routing_key.key1";
        //消息是byte[]，可以传递所有类型（转换为byte[]），不局限于字符串
        try {
            channel.basicPublish(RabbitConstant.EXCHANGE_NAME, routing_key, null, msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            channelEntry.getConnectionEntry().changeStatus();
        }
        System.out.println("send message：" + msg);
    }

}
