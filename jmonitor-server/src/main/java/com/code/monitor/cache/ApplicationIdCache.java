package com.code.monitor.cache;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.code.monitor.dao.ApplicationEntityRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/24 21:52
 *
 * 用作 appId 的本地缓存
 */
@Component
@Data
@Slf4j
public class ApplicationIdCache {

    /**
     * 缓存所有的 appId
     * 这里并没有采用并发的手段，因为数据的消费是串行的，所以并没有使用并行的手法
     */
    private final Set<String> cache = new ConcurrentHashSet<String>(16);

    @Autowired
    ApplicationEntityRepository repository;


    @PostConstruct
    public void init() {
        cache.addAll(repository.findAllAppId());
        log.info("applicationId 缓存初始化！");
    }

}
