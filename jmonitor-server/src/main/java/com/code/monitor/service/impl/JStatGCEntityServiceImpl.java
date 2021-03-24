package com.code.monitor.service.impl;

import com.code.monitor.dao.JStatGCRepository;
import com.code.monitor.entity.JStatGCEntity;
import com.code.monitor.service.JStatGCEntityService;
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
