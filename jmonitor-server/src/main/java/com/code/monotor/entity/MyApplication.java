package com.code.monotor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/20 20:06
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "application")
@Builder
public class MyApplication {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "app_min_memory")
    private String appMinMemory;

    @Column(name = "app_max_memory")
    private String appMaxMemory;

    @Column(name = "beat_heart")
    private LocalDateTime beatHeart;

}
