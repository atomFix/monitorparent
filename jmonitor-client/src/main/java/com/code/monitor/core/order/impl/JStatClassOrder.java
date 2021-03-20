package com.code.monitor.core.order.impl;

import com.code.monitor.core.constant.JavaOptionConstant;
import com.code.monitor.core.constant.ProcessConstant;
import com.code.monitor.core.entity.KVEntity;
import com.code.monitor.core.order.Order;
import com.code.monitor.core.util.JStatUtil;

import java.util.List;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/26 22:29
 */
public class JStatClassOrder implements Order {

    /**
     * 类加载信息
     * 轴为时间，Y轴为值的变化
     *
     * @return 类加载信息
     */
    @Override
    public Object getInfo() throws Exception {
        List<KVEntity> jstatClass = JStatUtil.jStat(new String[]{JavaOptionConstant.J_STAT,
                JavaOptionConstant.CLASS, ProcessConstant.PID});
        List<KVEntity> jstatCompiler = JStatUtil.jStat(new String[]{JavaOptionConstant.J_STAT,
                JavaOptionConstant.COMPILER, ProcessConstant.PID});
        jstatClass.addAll(jstatCompiler);
        return jstatClass;
    }

    @Override
    public String getOrderName() {
        return "JStatClass";
    }

}
