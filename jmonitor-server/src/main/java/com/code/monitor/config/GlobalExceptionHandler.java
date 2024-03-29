package com.code.monitor.config;

import com.code.monitor.exception.code.CodeMsg;
import com.code.monitor.exception.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        log.info("异常拦截器拦截成功！");
        //绑定异常是需要明确提示给用户的
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            List<ObjectError> errors = exception.getAllErrors();
            //获取自错误信息
            String msg = errors.get(0).getDefaultMessage();
            //将具体错误信息设置到CodeMsg中返回
            return Result.error(CodeMsg.SERVER_BIND_ERROR.fillArgs(msg));
        }
        // 其余异常简单返回为服务器异常
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}