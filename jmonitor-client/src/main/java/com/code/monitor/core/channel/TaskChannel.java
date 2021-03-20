package com.code.monitor.core.channel;

import com.alibaba.fastjson.JSON;
import com.code.monitor.core.order.Order;
import com.code.monitor.core.order.enums.OrderEnum;
import com.code.monitor.mq.rabbit.impl.RabbitServiceImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/26 23:50
 */
public class TaskChannel {

    private static final TaskChannel CHANNEL = new TaskChannel();
    final Object obj = new Object();
    List<Order> orderList = new LinkedList<>();
    RabbitServiceImpl rabbitTemplate = RabbitServiceImpl.RABBIT_TEMPLATE;

    /**
     * 得到指令链对象
     *
     * @return TaskChannel
     */
    public static TaskChannel getChannel() {
        return CHANNEL;
    }

    /**
     * 添加一个指令
     *
     * @param order
     */
    public void addLast(Order order) {
        synchronized (obj) {
            orderList.add(order);
        }
    }

    /**
     * 执行指令
     */
    public void exec() {
        HashMap<String, Object> map = new HashMap<>(8);
        orderList.forEach(order -> {
            try {
                Map<String, Object> object = order.getObject();
                map.put(order.getOrderName(), object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        rabbitTemplate.send(JSON.toJSONString(map));
    }

    /**
     * 组装指令
     */
    public void packaging() {
        addLast(OrderEnum.J_INFO.getOrder());
        addLast(OrderEnum.J_STAT_GC.getOrder());
        addLast(OrderEnum.J_STAT_MEMORY.getOrder());
    }
}
