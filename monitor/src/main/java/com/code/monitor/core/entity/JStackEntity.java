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
public class JStackEntity implements Serializable {
    private static final long serialVersionUID = -6029754500300872233L;
    private String id;
    private int total;
    private int RUNNABLE;
    private int TIMED_WAITING;
    private int WAITING;
}
