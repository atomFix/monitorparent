package com.code.monitor.dao;

import com.code.monitor.entity.JStatGCEntity;
import com.code.monitor.view.vo.JStatGCVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 20:29
 */
public interface JStatGCRepository extends JpaRepository<JStatGCEntity, Long> {

    @Query(value = "select app.* from jstatgc app where app.app_id = ?1 order by app.record_time desc limit ?2",
            nativeQuery = true)
    List<JStatGCEntity> selectGCEntityLimited(String appId, Integer number);

}
