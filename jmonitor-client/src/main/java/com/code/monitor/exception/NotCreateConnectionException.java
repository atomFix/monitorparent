package com.code.monitor.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/29 17:34
 */
@Slf4j
public class NotCreateConnectionException extends Exception {
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        log.error("rabbitmq 连接获取失败");
    }
}
