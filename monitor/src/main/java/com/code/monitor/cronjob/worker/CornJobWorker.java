package com.code.monitor.cronjob.worker;

import com.code.monitor.cronjob.factory.CornJobThreadFactory;
import com.code.monitor.properties.PropertiesConfig;
import com.code.monitor.properties.constant.ThreadPoolConstant;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/26 21:25
 */
public class CornJobWorker {

    static final int THREAD_POOL_SIZE = 1;
    /**
     * 周期性执行任务的线程池
     */
    static final ScheduledExecutorService THREAD_POOL = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
    /**
     * 处理紧急任务的线程池
     */
    static final ThreadPoolExecutor URGENT_TASK_THREAD_POOL = new ThreadPoolExecutor(2,
            2, 0,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
            new CornJobThreadFactory());
    /**
     * 等待被执行的任务
     */
    private static Runnable runnable;

    public static void setRunnable(Runnable runnable) {
        CornJobWorker.runnable = runnable;
    }

    /**
     * 定时任务启动
     *
     * @param state        定时任务状态
     */
    public static void start(boolean state) {
        HashMap<String, String> configMap = PropertiesConfig.configMap;
        int initialDelay = Integer.parseInt(configMap.getOrDefault(ThreadPoolConstant.INITIAL_DELAY, "5"));
        int period = Integer.parseInt(configMap.getOrDefault(ThreadPoolConstant.PERIOD, "1"));
        if (state) {
            THREAD_POOL.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS);
        }
    }

    public static void shutdown() {
        THREAD_POOL.shutdownNow();
    }

}
