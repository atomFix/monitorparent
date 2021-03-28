package com.code.monitor.service;

import com.code.monitor.entity.JStatGCEntity;
import com.code.monitor.view.vo.JStatGCVO;

import java.util.List;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 20:56
 */
public interface JStatGCEntityService extends BasicService<JStatGCEntity> {

    List<JStatGCVO> getLastGCEntity(String appId, Integer number);

}
