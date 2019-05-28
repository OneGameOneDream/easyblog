package com.ggiit.easyblog.common.exception;


import lombok.NoArgsConstructor;

/**
 * 用户名已存在异常
 *
 * @author gao
 * @date 2019.5.9
 */
@NoArgsConstructor
public class EntityExistException extends RuntimeException {

    private String msg;

    public EntityExistException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
