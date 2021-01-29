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
 * @date 2021/1/26 22:42
 */
public class JStatGCOrder implements Order {

    /**
     * 堆内存信息
     * X轴为时间，Y轴为值的变化
     *
     * @return 堆内存信息
     */
    @Override
    public Object getInfo() throws Exception {
        return JStatUtil.jStat(new String[]{JavaOptionConstant.J_STAT,
                JavaOptionConstant.GC, ProcessConstant.PID});
    }

    @Override
    public String getOrderName() {
        return "JStatGC";
    }

}
