package com.code.monotor.dao;

import com.code.monotor.entity.JStatGCEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 20:29
 */
public interface JStatGCRepository extends JpaRepository<JStatGCEntity, Long> {
}