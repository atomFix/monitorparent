package com.code.monitor.security.service.impl;

import com.code.monitor.security.dao.UserRepository;
import com.code.monitor.security.entity.UserEntity;
import com.code.monitor.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/29 17:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity addUser(UserEntity user) {
        if (user.getUsername() != null && user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userRepository.save(user);
        } else {
            user = null;
        }
        return user;
    }

    @Override
    public UserEntity getUser(String username) {
        return userRepository.findByUsername(username);
    }

}
