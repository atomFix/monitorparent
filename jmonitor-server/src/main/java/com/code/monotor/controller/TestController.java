package com.code.monotor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/2/17 15:36
 */
@RestController
public class TestController {

    @RequestMapping("/try")
    public String test() {
        return "1";
    }

}
