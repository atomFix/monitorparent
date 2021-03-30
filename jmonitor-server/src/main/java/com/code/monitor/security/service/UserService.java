package com.code.monitor.security.service;

import com.code.monitor.security.entity.UserEntity;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/29 17:02
 */
public interface UserService {

    UserEntity getUser(String username);

    UserEntity addUser(UserEntity user);
}
