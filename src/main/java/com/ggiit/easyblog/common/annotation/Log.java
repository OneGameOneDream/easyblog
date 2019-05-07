package com.ggiit.easyblog.common.annotation;


import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author gao
 * @date 2019.5.7
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 方法描述
     *
     * @return 方法描述
     */
    String value() default "无方法描述";

}
