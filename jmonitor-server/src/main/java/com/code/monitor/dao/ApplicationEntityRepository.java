package com.code.monitor.dao;

import com.code.monitor.entity.ApplicationEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/20 20:MyApplicationRepository
 */
public interface ApplicationEntityRepository extends JpaRepository<ApplicationEntity, Long> {

    ApplicationEntity findByAppId(String appId);

    @Modifying
    @Transactional
    @Query("update ApplicationEntity app set app.beatHeart = ?1,app.effective = 0" +
            " where app.appId = ?2")
    int updateBeatHeartByAppId(LocalDateTime beatHeart, String appId);

    /**
     * 查找小于传入时间的application
     *
     * @param beatHeart
     * @return
     */
    @Query("select new com.code.monitor.entity.ApplicationEntity(app.appId, app.beatHeart) from ApplicationEntity app where app.effective = 0 and app.beatHeart < ?1")
    List<ApplicationEntity> findAllApp(LocalDateTime beatHeart);

    /**
     * 修改状态
     *
     * @param dateTime 时间
     * @return 修改的行数
     */
    @Modifying
    @Transactional
    @Query("update ApplicationEntity app set app.effective = 1 where app.beatHeart < ?1")
    int changeEffective(LocalDateTime dateTime);

    @Query("select app.appId from ApplicationEntity app")
    List<String> findAllAppId();

}
