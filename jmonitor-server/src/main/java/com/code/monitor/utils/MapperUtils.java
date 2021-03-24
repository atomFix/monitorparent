package com.code.monitor.utils;

import com.code.monitor.constant.Constant;
import com.code.monitor.entity.ApplicationEntity;
import com.code.monitor.entity.JStatGCEntity;
import com.code.monitor.entity.JStatMemoryEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 16:58
 */
public class MapperUtils {

    public static JStatGCEntity saveStatGC(String appId, LocalDateTime dateTime, List<KVEntity> kvEntities) {
        JStatGCEntity entity = new JStatGCEntity();
        entity.setAppId(appId);
        entity.setRecordTime(dateTime);
        entity.setS0C(kvEntities.get(0).getValue());
        entity.setS1C(kvEntities.get(1).getValue());
        entity.setS0U(kvEntities.get(2).getValue());
        entity.setS1U(kvEntities.get(3).getValue());
        entity.setEC(kvEntities.get(4).getValue());
        entity.setEU(kvEntities.get(5).getValue());
        entity.setOC(kvEntities.get(6).getValue());
        entity.setOU(kvEntities.get(7).getValue());
        entity.setMC(kvEntities.get(8).getValue());
        entity.setMU(kvEntities.get(9).getValue());
        entity.setCCSC(kvEntities.get(10).getValue());
        entity.setCCSU(kvEntities.get(11).getValue());
        entity.setYGC(kvEntities.get(12).getValue());
        entity.setYGCT(kvEntities.get(13).getValue());
        entity.setFGC(kvEntities.get(14).getValue());
        entity.setFGCT(kvEntities.get(15).getValue());
        entity.setGCT(kvEntities.get(16).getValue());
        return entity;
    }

    public static JStatMemoryEntity saveStatMemory(String appId, LocalDateTime dateTime, List<KVEntity> kvEntities) {
        return JStatMemoryEntity.builder().appId(appId)
                .recordTime(dateTime)
                .s0(kvEntities.get(0).getValue())
                .s1(kvEntities.get(1).getValue())
                .e(kvEntities.get(2).getValue())
                .o(kvEntities.get(3).getValue())
                .m(kvEntities.get(4).getValue())
                .ccs(kvEntities.get(5).getValue())
                .ygc(kvEntities.get(6).getValue())
                .ygct(kvEntities.get(7).getValue())
                .fgc(kvEntities.get(8).getValue())
                .fgct(kvEntities.get(9).getValue())
                .gct(kvEntities.get(10).getValue())
                .build();
    }

    public static ApplicationEntity saveApplicationInfo(String appId, LocalDateTime dateTime, List<KVEntity> kvEntities) {
        String maxHeapSize = null, maxNewSize = null, oldSize = null;
        for (KVEntity kvEntity : kvEntities) {
            if (Constant.MAX_HEAP_SIZE.equals(kvEntity.getKey())) {
                maxHeapSize = kvEntity.getValue();
            }
            if (Constant.MAX_NEW_SIZE.equals(kvEntity.getKey())) {
                maxNewSize = kvEntity.getValue();
            }
            if (Constant.MAX_OLD_SIZE.equals(kvEntity.getKey())) {
                oldSize = kvEntity.getValue();
            }
        }
        return ApplicationEntity.builder().appId(appId)
                .beatHeart(dateTime)
                .appMaxHeap(maxHeapSize)
                .appNewSize(maxNewSize)
                .appOldSize(oldSize)
                .build();
    }


}
