package com.code.monitor.core.order.enums;

import com.code.monitor.core.order.Order;
import com.code.monitor.core.order.impl.*;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/27 20:05
 */
public enum OrderEnum {
    /**
     * J_INFO
     */
    J_INFO(new JInfoOrder()),
    /**
     * J_STAT_CLASS
     */
    J_STAT_CLASS(new JStatClassOrder()),
    /**
     * J_STAT_GC
     */
    J_STAT_GC(new JStatGCOrder()),
    /**
     * J_STAT_MEMORY
     */
    J_STAT_MEMORY(new JStatMemoryOrder()),
    /**
     * J_STACK
     */
    J_STACK(new JStackOrder()),
    /**
     * J_VSERSION
     */
    J_VSERSION(new JavaVersionOrder());

    private Order order;

    public Order getOrder() {
        return order;
    }

    /**
     * 构造函数
     * @param order
     */
    OrderEnum(Order order) {
        this.order = order;
    }
}
