package com.code.monitor.security.dao;

import com.code.monitor.security.entity.UserEntity;
import com.sun.jndi.toolkit.ctx.StringHeadTail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/29 16:06
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);

}
