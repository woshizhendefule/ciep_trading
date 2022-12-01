package com.chengyu.ciep_trading.common;

/**
 * 常用API返回对象接口
 *
 * @author CL
 */
public interface IErrorCode {
    /**
     * 状态代码code Getter
     *
     * @return code int值
     */
    public int getCode();

    /**
     * 状态简要信息message Getter
     *
     * @return message String值
     */
    public String getMessage();

    /**
     * 状态描述description Getter
     *
     * @return description String值
     */
    public String getDescription();
}