package com.ggiit.easyblog.framework.web.exception;

import com.ggiit.easyblog.common.constant.ResponseCode;
import com.ggiit.easyblog.common.exception.*;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
     * 用户名已存在异常
     */
    @ExceptionHandler(UsernameExistException.class)
    public ApiResult usernameExistException() {
        return ApiResult.error(ResponseCode.USERNAME_EXIST);
    }


    /**
     * 角色名已存在异常
     */
    @ExceptionHandler(RoleNameExistException.class)
    public ApiResult roleNameExistException() {
        return ApiResult.error(ResponseCode.ROLE_NAME_EXIST);
    }

    /**
     * 角色名已存在异常
     */
    @ExceptionHandler(MenuNameExistException.class)
    public ApiResult menuNameExistException() {
        return ApiResult.error(ResponseCode.MENU_NAME_EXIST);
    }

    /**
     * Email已存在异常
     */
    @ExceptionHandler(EmailExistException.class)
    public ApiResult emailExistException() {
        return ApiResult.error(ResponseCode.EMAIL_EXIST);
    }


    /**
     * 用户未找到
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResult usernameNotFoundException() {
        return ApiResult.error(ResponseCode.USER_NOT_FOUND);
    }

    /**
     * 接口无权访问
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResult accessDeniedException() {
        return ApiResult.error(ResponseCode.NO_PERMISSION);
    }

    /**
     * 初始化JwtUser失败
     */
    @ExceptionHandler(InitJwtUserException.class)
    public ApiResult initJwtUserException() {
        return ApiResult.error(ResponseCode.INIT_JWT_USER_ERROR);
    }

    /**
     * Token解析失败
     */
    @ExceptionHandler(TokenException.class)
    public ApiResult tokenException() {
        return ApiResult.error(ResponseCode.TOKEN_ERROR);
    }

    /**
     * Token认证失败
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ApiResult expiredJwtException() {
        return ApiResult.error(ResponseCode.AUTH_ERROR);
    }

    /**
     * 密碼錯誤
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ApiResult badCredentialsException() {
        return ApiResult.error(ResponseCode.PASSWORD_ERROR);
    }


    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResult handleException() {
        return ApiResult.error(ResponseCode.SERVER_ERROR);
    }

}
