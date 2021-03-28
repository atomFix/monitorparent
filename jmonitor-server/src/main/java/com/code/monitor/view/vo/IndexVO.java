package com.code.monitor.view.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/28 9:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexVO {

    private Long id;

    private String appId;

    private Byte effective;

}
