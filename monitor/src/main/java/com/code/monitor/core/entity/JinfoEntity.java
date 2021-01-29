package com.code.monitor.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author codedorado
 * @date 2021/01/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JinfoEntity {
    private List<String> noedefault;
    private List<String> commandLine;
}
