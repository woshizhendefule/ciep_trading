package com.chengyu.ciep_trading.exception;

import com.chengyu.ciep_trading.common.ResultCode;
import lombok.Data;

/**
 * 自定义封装异常
 *
 * @author CL
 */
@Data
public class BusinessException extends RuntimeException {
    /**
     * 状态代码
     */
    private final int code;

    /**
     * 状态描述
     */
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.description = resultCode.getDescription();
    }

    public BusinessException(ResultCode resultCode, String description) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.description = description;
    }
}
