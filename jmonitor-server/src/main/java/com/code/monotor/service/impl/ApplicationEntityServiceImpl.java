package com.code.monotor.service.impl;

import com.code.monotor.dao.ApplicationEntityRepository;
import com.code.monotor.entity.ApplicationEntity;
import com.code.monotor.service.ApplicationEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 20:33
 */
@Service
@Slf4j
public class ApplicationEntityServiceImpl implements ApplicationEntityService {

    @Autowired
    ApplicationEntityRepository applicationEntityRepository;

    @Override
    public void save(ApplicationEntity applicationEntity) {
        applicationEntityRepository.save(applicationEntity);
    }
}
