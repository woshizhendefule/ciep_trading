package com.chengyu.ciep_trading.common;

/**
 * 返回工具类
 *
 * @author CL
 */
public class ResultUtils {
    /**
     * 成功
     *
     * @param data 返回数据
     * @param <T>  Type(Java类)
     * @return 通用返回对象
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功
     *
     * @param description 状态描述
     * @param data        返回数据
     * @param <T>         Type(Java类)
     * @return 通用返回对象
     */
    public static <T> BaseResponse<T> success(String description, T data) {
        return new BaseResponse<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), description, data);
    }

    /**
     * 失败
     *
     * @param resultCode 状态信息
     * @param <T>        Type(Java类)
     * @return 通用返回对象
     */
    public static <T> BaseResponse<T> error(ResultCode resultCode) {
        return new BaseResponse<>(resultCode);
    }

    /**
     * 失败
     *
     * @param resultCode  状态信息
     * @param description 状态描述
     * @param <T>         Type(Java类)
     * @return 通用返回对象
     */
    public static <T> BaseResponse<T> error(ResultCode resultCode, String description) {
        return new BaseResponse<>(resultCode, description);
    }

    /**
     * 失败
     *
     * @param code        状态代码
     * @param message     状态简要信息
     * @param description 状态描述
     * @param <T>         Type(Java类)
     * @return 通用返回对象
     */
    public static <T> BaseResponse<T> error(int code, String message, String description) {
        return new BaseResponse<>(code, message, description);
    }
}
