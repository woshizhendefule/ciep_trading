package com.chengyu.ciep_trading.common;

/**
 * 常用API返回对象
 *
 * @author CL
 */
public enum ResultCode implements IErrorCode {
    /**
     * 预设状态信息
     */
    SUCCESS(200, "操作成功", ""),

    FAILED(40500, "操作失败", ""),

    PARAMS_ERROR(40401, "请求参数错误", ""),

    TOKEN_ERROR(40402, "Token错误", ""),

    SYSTEM_ERROR(50000, "系统内部异常", "");

    /**
     * 状态代码
     */
    private final int code;

    /**
     * 状态简要信息
     */
    private final String message;

    /**
     * 状态描述
     */
    private final String description;

    ResultCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}