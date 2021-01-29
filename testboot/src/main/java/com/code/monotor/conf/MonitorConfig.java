package com.code.monotor.conf;

import com.code.monitor.core.order.impl.Jmap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/26 19:17
 */
@Configuration
public class MonitorConfig {

    public Jmap getJMap() {
        return new Jmap();
    }

}
