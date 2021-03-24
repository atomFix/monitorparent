package com.code.monitor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/22 17:47
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jstatmemory")
@Builder
public class JStatMemoryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String appId;
    private LocalDateTime recordTime;
    /**
     * Heap上的 Survivor space 0 区已使用空间的百分比
     */
    @Column(name = "s0")
    private String s0;
    /**
     * Heap上的 Survivor space 1 区已使用空间的百分比
     */
    @Column(name = "s1")
    private String s1;
    /**
     * Heap上的 Eden space区已使用空间的百分比
     */
    @Column(name = "e")
    private String e;
    /**
     * Heap上的 Old space区已使用空间的百分比
     */
    @Column(name = "o")
    private String o;
    /**
     * 元空间占比
     */
    @Column(name = "m")
    private String m;

    /**
     * 压缩类空间利用率为百分比
     */
    @Column(name = "ccs")
    private String ccs;

    /**
     * 从应用程序启动到采样时发生 Young GC 的次数
     */
    @Column(name = "ygc")
    private String ygc;
    /**
     * 从应用程序启动到采样时 Young GC 所用的时间(单位秒)
     */
    @Column(name = "ygct")
    private String ygct;
    /**
     * 从应用程序启动到采样时发生 Full GC 的次数
     */
    @Column(name = "fgc")
    private String fgc;
    /**
     * 从应用程序启动到采样时 Full GC 所用的时间(单位秒)
     */
    @Column(name = "fgct")
    private String fgct;
    /**
     * 从应用程序启动到采样时用于垃圾回收的总时间(单位秒)
     */
    @Column(name = "gct")
    private String gct;

}
