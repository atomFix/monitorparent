package com.code.monitor.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author codedorado
 * @date 2021/01/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KVEntity implements Serializable {
    private static final long serialVersionUID = 4197672630187648596L;
    private String key;
    private String value;
}
