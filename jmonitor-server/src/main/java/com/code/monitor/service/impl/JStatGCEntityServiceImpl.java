package com.code.monitor.service.impl;

import com.code.monitor.dao.JStatGCRepository;
import com.code.monitor.entity.JStatGCEntity;
import com.code.monitor.service.JStatGCEntityService;
import com.code.monitor.view.vo.JStatGCVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<JStatGCVO> getLastGCEntity(String appId, Integer number) {
        List<JStatGCEntity> jStatGCVOS = jStatGCRepository.selectGCEntityLimited(appId, number);
        List<JStatGCVO> collect = jStatGCVOS.stream().map(entity -> {
            JStatGCVO vo = new JStatGCVO();
            BeanUtils.copyProperties(entity, vo);
            vo.setDate(entity.getRecordTime().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            return vo;
        }).collect(Collectors.toList());
        return Lists.reverse(collect);
    }
}
