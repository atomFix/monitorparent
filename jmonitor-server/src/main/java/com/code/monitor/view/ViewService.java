package com.code.monitor.view;

import com.code.monitor.dao.ApplicationEntityRepository;
import com.code.monitor.entity.ApplicationEntity;
import com.code.monitor.view.vo.IndexVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/28 8:42
 */
@Service
public class ViewService {

    @Autowired
    ApplicationEntityRepository repository;

    /**
     * 获取所有信息列表
     *
     * @return List<ApplicationEntity>
     */
    public List<ApplicationEntity> getApplications() {
        return repository.findAll();
    }

    /**
     * 获取app列表
     *
     * @return IndexVO
     */
    public List<IndexVO> getIndex() {
        List<ApplicationEntity> apps = repository.findAll();
        return apps.stream().map(app -> new IndexVO(app.getId(), app.getAppId(), app.getEffective()))
                .collect(Collectors.toList());
    }

}
