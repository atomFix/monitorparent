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
public class TestC {

    @Autowired
    UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertC() {
        userMapper.insertUser("C_4");
        throw new NullPointerException();
    }

}
