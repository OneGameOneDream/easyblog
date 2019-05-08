package com.ggiit.easyblog.framework.web.exception;

import com.ggiit.easyblog.common.constant.ResponseCode;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author gao
 * @date 2019.5.8
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        return ApiResult.error(ResponseCode.SERVER_ERROR);
    }

}
