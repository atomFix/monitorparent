package com.code.monitor.exception.result;

import com.code.monitor.exception.code.CodeMsg;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/27 10:56
 */
public class Result<T> {

    private CodeMsg codeMsg;

    public Result(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }

    public static Result error(CodeMsg codeMsg) {
        return new Result(codeMsg);
    }

}
