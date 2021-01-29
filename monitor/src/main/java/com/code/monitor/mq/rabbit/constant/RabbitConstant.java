package com.code.monitor.mq.rabbit.constant;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/28 21:17
 */
public interface RabbitConstant {

    //队列名称
    String QUEUE_NAME = "data_queue";
    //要使用的exchange的名称
    String EXCHANGE_NAME = "data_exchange";
    //要使用的exchange的名称
    String EXCHANGE_TYPE = "topic";
    /**
     * exchange使用的routing-key
     */
    String EXCHANGE_ROUTING_KEY = "data_routing_key.#";

}
