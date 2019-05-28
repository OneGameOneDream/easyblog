package com.ggiit.easyblog.common.exception;

import lombok.NoArgsConstructor;

/**
 * 未找到实体错误
 *
 * @author gao
 * @date 2019.5.28
 */
@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException {

    private String msg;

    public EntityNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
