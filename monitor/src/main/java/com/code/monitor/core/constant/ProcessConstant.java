package com.code.monitor.core.constant;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/26 21:09
 * <p>
 * 保存线程相关信息
 */
public interface ProcessConstant {
    /**
     * 项目进程号
     */
    String PID = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
}
