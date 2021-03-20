package com.code.monotor.dao;

import com.code.monotor.entity.MyApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/20 20:MyApplicationRepository
 */
public interface MyApplicationRepository extends JpaRepository<MyApplication, Long> {
}
