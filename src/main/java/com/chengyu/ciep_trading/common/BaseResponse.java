package com.chengyu.ciep_trading.common;

import lombok.Data;

/**
 * 通用返回对象
 *
 * @author CL
 */
@Data
public class BaseResponse<T> {
    /**
     * 状态代码
     */
    private int code;

    /**
     * 状态简要信息
     */
    private String message;

    /**
     * 状态描述
     */
    private String description;

    /**
     * 方法自定状态描述
     */
    private T data;

    public BaseResponse(int code, String message, String description, T data) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.data = data;
    }

    public BaseResponse(int code, String message, T data) {
        this(code, message, null, data);
    }

    public BaseResponse(int code, String message, String description) {
        this(code, message, description, null);
    }

    public BaseResponse(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage(), resultCode.getDescription(), null);
    }

    public BaseResponse(ResultCode resultCode, String description) {
        this(resultCode.getCode(), resultCode.getMessage(), description, null);
    }
}