package com.code.monitor.controller;


import com.code.monitor.entity.ApplicationEntity;
import com.code.monitor.view.ViewService;
import com.code.monitor.view.vo.IndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 23:29
 */
@Controller
public class ViewController {
    // @Autowired
    // private AddressParm addressParm;
    // @Autowired
    // private ViewService viewService;

    @Autowired
    ViewService viewService;

    @RequestMapping(value = "/")
    public String index(ModelMap model) throws IOException {
        List<IndexVO> list = viewService.getIndex();
        list.forEach(System.out::println);
        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping(value = "/main")
    public String main(ModelMap model) throws IOException {
        List<ApplicationEntity> list = viewService.getApplications();
        model.addAttribute("list", list);
        return "main";
    }

    @RequestMapping(value = "/monitor")
    public String monitor() {
        return "monitor";
    }


}
