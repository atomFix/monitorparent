package com.code.monitor.cornjob.task;

import com.code.monitor.cornjob.service.ApplicationStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/24 20:42
 */
@Component
@Slf4j
public class StatusChangedTask {

    @Autowired
    ApplicationStatusService applicationStatusService;

    AtomicInteger counter = new AtomicInteger(0);

    @Scheduled(cron = "0 0/1 * * * ?")
    public void checkStatus() {
        log.info("定时任务执行的第 {} 次", counter.incrementAndGet());
        if (counter.get() >= Integer.MAX_VALUE - 8) {
            counter.set(0);
        }
        applicationStatusService.execute();
    }

}
