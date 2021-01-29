package com.code.monitor.mq;

import java.util.Objects;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/27 22:50
 */
public interface MessageQueueService {

    void send(String obj);

}
