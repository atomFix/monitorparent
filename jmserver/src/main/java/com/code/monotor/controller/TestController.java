package com.code.monotor.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/2/17 15:36
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/try")
    public String test(String string) {
        if (!StringUtils.hasLength(string)) {
            return "error";
        }
        return string;
    }

}
