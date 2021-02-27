package com.code.monotor.service;

import com.code.monotor.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/2/24 16:00
 */
@Service
public class TestB {
    @Autowired
    UserMapper userMapper;

    @Autowired
    TestC testC;


    @Transactional(rollbackFor = Exception.class)
    public void insertB() {
        userMapper.insertUser("B_4");
        testC.insertC();
    }

}
