package com.code.monitor.core.order.impl;


import com.alibaba.fastjson.JSONObject;
import com.code.monitor.core.cmd.ExecuteCmd;
import com.code.monitor.core.constant.JavaOptionConstant;
import com.code.monitor.core.constant.MagicConstant;
import com.code.monitor.core.constant.ProcessConstant;
import com.code.monitor.core.entity.JinfoEntity;
import com.code.monitor.core.order.Order;
import com.code.monitor.core.util.ArrayUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author codedorado
 * @date 2021/01/26
 */
public class JInfoOrder implements Order {

    /**
     * JVM默认参数与指定参数
     *
     * @return
     */
    private JinfoEntity info() {
        String s = ExecuteCmd.execute(new String[]{JavaOptionConstant.J_INFO, JavaOptionConstant.FLAGS, ProcessConstant.PID});
        if (s != null && !s.contains("successfully")) {
            return null;
        }
        String flags = MagicConstant.SUCCESSFULLY;
        String command = MagicConstant.COMMAND;
        //默认参数
        String[] noedefault = ArrayUtil.trim(s.substring(s.indexOf(flags) + flags.length(), s.indexOf(command)).split("\\s+"));
        String[] commandLine = null;
        s = s.substring(s.indexOf(command));
        if (!s.equals(command)) {
            commandLine = s.substring(command.length()).split("\\s+");
        }
        commandLine = ArrayUtil.trim(commandLine);
        return new JinfoEntity(Arrays.stream(noedefault).collect(Collectors.toList()), Arrays.stream(commandLine).collect(Collectors.toList()));
    }

    /**
     * 获取 T 对象的信息
     *
     * @return T
     * @throws Exception 可预测异常
     */
    @Override
    public Object getInfo() throws Exception {
        return info();
    }

    @Override
    public String getOrderName() {
        return "JInfo";
    }
}
