package com.code.monotor.service.impl;

import com.code.monotor.dao.JStatMemoryRepository;
import com.code.monotor.entity.JStatMemoryEntity;
import com.code.monotor.service.JStatMemoryEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 21:02
 */
@Service
@Slf4j
public class JStatMemoryEntityEntityServiceImpl implements JStatMemoryEntityService {

    @Autowired
    JStatMemoryRepository jStatMemoryRepository;

    @Override
    public void save(JStatMemoryEntity instance) {
        jStatMemoryRepository.save(instance);
    }
}
