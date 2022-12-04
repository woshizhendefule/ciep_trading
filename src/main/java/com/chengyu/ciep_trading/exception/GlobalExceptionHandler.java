package com.chengyu.ciep_trading.exception;

import com.chengyu.ciep_trading.common.BaseResponse;
import com.chengyu.ciep_trading.common.ResultCode;
import com.chengyu.ciep_trading.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author CL
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException " + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> businessExceptionHandler(RuntimeException e) {
        log.error("RuntimeException " + e.getMessage(), e);
        return ResultUtils.error(ResultCode.SYSTEM_ERROR, e.getMessage());
    }
}