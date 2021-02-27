package com.code.monotor.controller;

import com.code.monotor.service.TestA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/2/17 15:36
 */
@RestController
public class TestController {

    @Autowired
    TestA testA;

    @RequestMapping("/try")
    public String test() {
        testA.insertA();
        return "1";
    }

}
