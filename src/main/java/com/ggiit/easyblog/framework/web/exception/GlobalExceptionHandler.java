package com.ggiit.easyblog.framework.web.exception;

import com.ggiit.easyblog.common.exception.EntityExistException;
import com.ggiit.easyblog.common.exception.EntityNotFoundException;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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
     * 实体已存在异常
     */
    @ExceptionHandler(EntityExistException.class)
    public ApiResult entityExistException(EntityExistException e) {
        return ApiResult.error(e.getMessage());
    }

    /**
     * 接口无权访问
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResult accessDeniedException() {
        return ApiResult.error(403,"无权访问");
    }


    /**
     * Token认证失败
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ApiResult expiredJwtException() {
        return ApiResult.error("Token认证失败");
    }


    /**
     * 密码错误
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ApiResult badCredentialsException() {
        return ApiResult.error("密码错误");
    }


    /**
     * 未找到对象
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResult entityNotFoundException(EntityNotFoundException e) {
        return ApiResult.error(404,e.getMessage());
    }


    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResult handleException() {
        return ApiResult.error("服务器异常");
    }


}
