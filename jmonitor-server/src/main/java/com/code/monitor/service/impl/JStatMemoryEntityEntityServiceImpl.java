package com.code.monitor.service.impl;

import com.code.monitor.dao.JStatMemoryRepository;
import com.code.monitor.entity.JStatMemoryEntity;
import com.code.monitor.service.JStatMemoryEntityService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void save(JStatMemoryEntity instance) {
        jStatMemoryRepository.save(instance);
    }
}
