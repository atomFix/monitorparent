package com.code.monitor.core.order.impl;


import com.code.monitor.core.cmd.ExecuteCmd;
import com.code.monitor.core.constant.ProcessConstant;
import com.code.monitor.core.entity.JStackEntity;
import com.code.monitor.core.order.Order;
import com.code.monitor.core.util.ArrayUtil;
import com.code.monitor.core.util.PathUtil;
import com.code.monitor.exception.DumpException;

import java.io.IOException;

/**
 * @author codedorado
 * @date 2021/01/26
 */
public class JStackOrder implements Order {
    private final static String PREFIX = "java.lang.Thread.State: ";

    /**
     * 该进程的线程信息
     * X轴为时间，Y轴为值的变化
     *
     * @return JstackEntity
     */
    private JStackEntity jStack() {
        String s = ExecuteCmd.execute(new String[]{"jstack", ProcessConstant.PID});
        int total = ArrayUtil.appearNumber(s, "nid=");
        int runnable = ArrayUtil.appearNumber(s, PREFIX + "RUNNABLE");
        int timedWaiting = ArrayUtil.appearNumber(s, PREFIX + "TIMED_WAITING");
        int waiting = ArrayUtil.appearNumber(s, PREFIX + "WAITING");
        return new JStackEntity(ProcessConstant.PID, total, runnable, timedWaiting, waiting);
    }

    /**
     * 导出线程快照
     *
     * @param id 进程id
     * @return file
     */
    public static String dump(String id) throws IOException {
        //判断NULL
        if (id == null || "".equals(id)) {
            throw new NullPointerException("参数id为NULL");
        }
        String path = PathUtil.getRootPath("dump/" + id + "_thread.txt");
        String s = ExecuteCmd.execute(new String[]{"jstack", id});
        //是否正常生成快照文件
        if (s == null || s.isEmpty()) {
            throw new DumpException(id);
        }
        // File file = new File(path);
        // FileOutputStream out = new FileOutputStream(file, true);
        // FileUtils.write(file, s, StandardCharsets.UTF_8);

        // File file = new File(path);
        // try(FileOutputStream out = new FileOutputStream(file, true);
        //     FileChannel channel = out.getChannel()) {
        //     ByteBuffer buffer = ByteBuffer.wrap(s.getBytes());
        //     buffer.put(s.getBytes());
        //     buffer.flip();
        //     channel.write(buffer);
        // }
        return s;
    }

    /**
     * 该进程的线程信息
     *
     * @return JStackEntity
     * @throws Exception 可预测异常
     */
    @Override
    public JStackEntity getInfo() throws Exception {
        return jStack();
    }

    @Override
    public String getOrderName() {
        return "JStack";
    }
}
