package com.code.monitor.cronjob.job;

import com.code.monitor.core.channel.TaskChannel;
import com.code.monitor.properties.PropertiesConfig;
import com.code.monitor.properties.constant.ThreadPoolConstant;
import lombok.SneakyThrows;

import java.util.HashMap;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/27 17:25
 * <p>
 * 定时任务具体的查询逻辑
 */
public class TimedThread implements Runnable {

    static final Object lock = new Object();
    static volatile boolean isRun = true;

    /**
     * 配置文件内容
     */
    TaskChannel taskChannel = TaskChannel.getChannel();

    public static void stop() {
        isRun = false;
    }

    public static void keepRun() {
        if (!isRun) {
            isRun = true;
            lock.notify();
        }
    }

    public void init() {
        taskChannel.packaging();
    }

    @SneakyThrows
    @Override
    public void run() {
        taskChannel.exec();
    }
}
