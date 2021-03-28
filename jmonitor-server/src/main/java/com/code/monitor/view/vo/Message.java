package com.code.monitor.view.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/28 18:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String appId;
    private Integer number;
}
