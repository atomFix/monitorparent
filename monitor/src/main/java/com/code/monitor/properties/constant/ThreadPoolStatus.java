package com.code.monitor.properties.constant;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/27 19:31
 */
public interface ThreadPoolStatus {

    Integer STATUS = 1;
    Integer START = STATUS << 1;
    Integer STOP = STATUS << 2;

}
