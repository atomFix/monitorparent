package com.code.monitor.view.vo;

import com.code.monitor.entity.JStatGCEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/28 20:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JStatGCVO extends JStatGCEntity {
    private String date;
}
