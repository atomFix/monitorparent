package com.code.monotor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.code.monotor.service.RestoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/20 18:48
 */
@Service
public class RestoreServiceImpl implements RestoreService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void restore(String message) {
        JSONObject x = JSON.parseObject(message);
        x.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }
}
