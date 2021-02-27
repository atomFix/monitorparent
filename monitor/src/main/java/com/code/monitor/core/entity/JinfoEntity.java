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
public class JinfoEntity implements Serializable {
    private static final long serialVersionUID = 6319858840461087554L;
    private List<String> noedefault;
    private List<String> commandLine;
}
