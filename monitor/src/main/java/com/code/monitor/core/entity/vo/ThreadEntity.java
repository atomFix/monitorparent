package com.code.monitor.core.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 86178
 */
@Data
public class ThreadEntity implements Serializable {
    private static final long serialVersionUID = -5892568173956759779L;
    private Integer id;
    //进程所在主机
    private String address;
    //进程ID
    private String name;
    //x：时间
    private String date;
    private int total;
    private int RUNNABLE;
    private int TIMED_WAITING;
    private int WAITING;
}
