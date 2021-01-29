package com.code.monitor.core.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 86178
 */
@Data
public class ClassLoadEntity implements Serializable {
    private static final long serialVersionUID = 9140307679160569578L;
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
    private String Loaded;
    private String Bytes1;
    private String Unloaded;
    private String Bytes2;
    private String Time1;
    private String Compiled;
    private String Failed;
    private String Invalid;
    private String Time2;

}
