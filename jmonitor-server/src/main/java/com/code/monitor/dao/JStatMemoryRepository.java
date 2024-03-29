package com.code.monitor.dao;

import com.code.monitor.entity.JStatMemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 20:30
 */
public interface JStatMemoryRepository extends JpaRepository<JStatMemoryEntity, Long> {
}
