package com.code.monotor.dao;

import com.code.monotor.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/20 20:MyApplicationRepository
 */
public interface ApplicationEntityRepository extends JpaRepository<ApplicationEntity, Long> {
}
