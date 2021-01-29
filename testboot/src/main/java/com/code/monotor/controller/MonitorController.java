package com.code.monotor.controller;

import com.code.monitor.core.entity.JinfoEntity;
import com.code.monitor.core.order.enums.OrderEnum;
import com.code.monitor.core.order.impl.JInfoOrder;
import com.code.monitor.core.order.impl.JStackOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/26 19:16
 */
@RestController
@RequestMapping("/test")
public class MonitorController {

    static final String PID;

    static {
        PID = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    }

    @RequestMapping("/dump")
    public void getDump(HttpServletResponse response) throws IOException {
        String dump = JStackOrder.dump(PID);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(dump.getBytes());
    }

    @RequestMapping("/version")
    public Object getJavaVersionOrder() throws Exception {
        return OrderEnum.J_VSERSION.getOrder().getInfo();
    }

    @RequestMapping("/info")
    public Object getInfo() throws Exception {
        return OrderEnum.J_INFO.getOrder().getInfo();
    }

    @RequestMapping("/stack")
    public Object getJStackOrder() throws Exception {
        return OrderEnum.J_STACK.getOrder().getInfo();
    }

    @RequestMapping("/class")
    public Object getJStatClassOrder() throws Exception {
        return OrderEnum.J_STAT_CLASS.getOrder().getInfo();
    }

    @RequestMapping("/gc")
    public Object getJStatGCOrder() throws Exception {
        return OrderEnum.J_STAT_GC.getOrder().getInfo();
    }

    @RequestMapping("/memory")
    public Object getJStatMemoryOrder() throws Exception {
        return OrderEnum.J_STAT_MEMORY.getOrder().getInfo();
    }

}
