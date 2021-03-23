package com.code.monotor.service.impl;

import com.code.monotor.dao.JStatGCRepository;
import com.code.monotor.dao.JStatMemoryRepository;
import com.code.monotor.entity.JStatGCEntity;
import com.code.monotor.service.JStatGCEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 21:04
 */
@Service
@Slf4j
public class JStatGCEntityServiceImpl implements JStatGCEntityService {

    @Autowired
    JStatGCRepository jStatGCRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(JStatGCEntity instance) {
        jStatGCRepository.save(instance);
    }
}
