package com.code.monitor.security.controller;

import com.code.monitor.security.entity.UserEntity;
import com.code.monitor.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/29 18:00
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/doRegister")
    public String register(UserEntity user) {
        userService.addUser(user);
        return "login";
    }

    /**
     * 判断是否已存在对象
     *
     * @param username username
     * @return Boolean
     */
    @RequestMapping("/checkUser")
    @ResponseBody
    public Boolean checkUser(String username) {
        return Objects.nonNull(userService.getUser(username));
    }

}
