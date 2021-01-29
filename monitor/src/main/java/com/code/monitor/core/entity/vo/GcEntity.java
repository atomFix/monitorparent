package com.code.monitor.core.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 86178
 */
@Data
public class GcEntity implements Serializable {
    private static final long serialVersionUID = -1901253495065212419L;
    private Integer id;
    /**
     * 进程所在主机
     */
    private String address;
    /**
     * 进程ID
     */
    private String name;
    /**
     * x：时间
     */
    private String date;
    private String S0C;
    private String S1C;
    private String S0U;
    private String S1U;
    private String EC;
    private String EU;
    private String OC;
    private String OU;
    /**
     * PC
     */
    private String MC;
    /**
     * PU
     */
    private String MU;
    private String CCSC;
    private String CCSU;
    private String YGC;
    private String YGCT;
    private String FGC;
    private String FGCT;
    private String GCT;
}
