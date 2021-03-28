package com.code.monitor.socket.controller;


import com.code.monitor.entity.JStatGCEntity;
import com.code.monitor.service.JStatGCEntityService;
import com.code.monitor.view.vo.JStatGCVO;
import com.code.monitor.view.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Create by yster@foxmail.com 2018/6/19/019 23:49
 */
@Controller
public class GreetingController {

    @Autowired
    JStatGCEntityService jStatGCEntityService;

    @MessageMapping("/gc")
    @SendTo("/topic/gc")
    public List<JStatGCVO> socketGc(Message message) throws Exception {
        return jStatGCEntityService.getLastGCEntity(message.getAppId(), message.getNumber());
    }

}
