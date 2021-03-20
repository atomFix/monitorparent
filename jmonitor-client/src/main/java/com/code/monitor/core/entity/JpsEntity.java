package com.code.monitor.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author codedorado
 * @date 2021/01/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpsEntity implements Serializable {
    private static final long serialVersionUID = -5931032385415809688L;
    /**
     * 全名
     */
    private String className;
    /**
     * 小名
     */
    private String smallName;
    /**
     * 参数
     */
    private List<String> parameters;

}
