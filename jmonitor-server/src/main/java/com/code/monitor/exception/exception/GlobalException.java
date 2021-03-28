package com.code.monitor.exception.exception;

import com.code.monitor.exception.code.CodeMsg;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/25 10:16
 */
@Data
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 4767547599612508175L;
    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg=codeMsg;

    }

}
