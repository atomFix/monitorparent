package com.code.monitor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Create by yster@foxmail.com 2018/11/12 0012 20:39
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jstatgc")
public class JStatGCEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String appId;
    private LocalDateTime recordTime;
    /**
     * 年轻代中第一个survivor（幸存区）的容量 (字节)
     */
    @Column(name = "s0c")
    private String S0C;
    /**
     * 年轻代中第二个survivor（幸存区）的容量 (字节)
     */
    @Column(name = "s1c")
    private String S1C;
    /**
     * 年轻代中第一个survivor（幸存区）目前已使用空间 (字节)
     */
    @Column(name = "s0u")
    private String S0U;
    /**
     * 年轻代中第二个survivor（幸存区）目前已使用空间 (字节)
     */
    @Column(name = "s1u")
    private String S1U;
    /**
     * 年轻代中Eden（伊甸园）的容量 (字节)
     */
    @Column(name = "ec")
    private String EC;
    /**
     * 年轻代中Eden（伊甸园）目前已使用空间 (字节)
     */
    @Column(name = "eu")
    private String EU;
    /**
     * Old代的容量 (字节)
     */
    @Column(name = "oc")
    private String OC;
    /**
     * Old代目前已使用空间 (字节)
     */
    @Column(name = "ou")
    private String OU;
    @Column(name = "mc")
    private String MC;
    @Column(name = "mu")
    private String MU;//PU
    @Column(name = "ccsc")
    private String CCSC;
    @Column(name = "ccsu")
    private String CCSU;
    /**
     * 从应用程序启动到采样时年轻代中gc次数
     */
    @Column(name = "ygc")
    private String YGC;
    /**
     * 从应用程序启动到采样时年轻代中gc所用时间(s)
     */
    @Column(name = "ygct")
    private String YGCT;
    /**
     * 从应用程序启动到采样时old代(全gc)gc次数
     */
    @Column(name = "fgc")
    private String FGC;
    /**
     * 从应用程序启动到采样时old代(全gc)gc所用时间(s)
     */
    @Column(name = "fgct")
    private String FGCT;
    /**
     * 从应用程序启动到采样时gc用的总时间(s)
     */
    @Column(name = "gct")
    private String GCT;

}
