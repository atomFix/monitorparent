package com.code.monitor.cornjob.service;

import com.code.monitor.dao.ApplicationEntityRepository;
import com.code.monitor.mail.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/24 20:47
 */
@Service
public class ApplicationStatusService {

    @Autowired
    ApplicationEntityRepository repository;
    @Autowired
    IMailService iMailService;

    /**
     * 具体定时任务的执行
     */
    public void execute() {
        repository.findAllApp(LocalDateTime.now().minusMinutes(1)).forEach(app -> {
            String message = "机器编号：" + app + "<br>" + "下线时间：" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
            iMailService.sendHtmlMail("1441043674@qq.com", "JMonitor系统机器下线通知", message);
        });
        repository.changeEffective(LocalDateTime.now().minusMinutes(1));
    }

}
