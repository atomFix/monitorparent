package com.code.monitor.cornjob.service;

import com.code.monitor.dao.ApplicationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/24 20:47
 */
@Service
public class ApplicationStatusService {

    @Autowired
    ApplicationEntityRepository repository;

    public void execute() {
        repository.changeEffective(LocalDateTime.now().minusMinutes(1));
    }

}
