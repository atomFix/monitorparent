package com.code.monitor.service.impl;

import com.code.monitor.cache.ApplicationIdCache;
import com.code.monitor.dao.ApplicationEntityRepository;
import com.code.monitor.entity.ApplicationEntity;
import com.code.monitor.service.ApplicationEntityService;
import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 20:33
 */
@Service
@Slf4j
public class ApplicationEntityServiceImpl implements ApplicationEntityService {

    @Autowired
    ApplicationEntityRepository appRepository;
    @Autowired
    ApplicationIdCache applicationIdCache;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ApplicationEntity applicationEntity) {
        // 如果本地缓存没有的话才会去新建一个 application 对象，减少数据库的查询
        if (!applicationIdCache.getCache().contains(applicationEntity.getAppId()) ||
                Objects.equal(appRepository.findByAppId(applicationEntity.getAppId()), null)) {
            appRepository.save(applicationEntity);
            // 将新来的 application 缓存进本地缓存里面
            applicationIdCache.getCache().add(applicationEntity.getAppId());
        } else {
            appRepository.updateBeatHeartByAppId(applicationEntity.getBeatHeart(), applicationEntity.getAppId());
        }
    }
}
