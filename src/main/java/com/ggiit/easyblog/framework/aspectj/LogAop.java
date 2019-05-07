package com.ggiit.easyblog.framework.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * 日志切面
 *
 * @author gao
 * @date 2019.5.7
 */
@Slf4j
@Aspect
@Component
public class LogAop {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.ggiit.easyblog.common.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 方法执行前
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        //开始计时
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印请求的内容
        //获取请求头中的User-Agent
        log.info("=================方法调用开始=================");
        log.info("请求路径：{}", request.getRequestURL().toString());
        log.info("IP : {}", request.getRemoteAddr());
        log.info("请求类型：{}", request.getMethod());
        log.info("类方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数 : {} " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 方法执行后
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        //处理完请求后，返回内容
        log.info("方法返回值：{}", ret);
        log.info("方法执行时间：{}毫秒", (System.currentTimeMillis() - startTime.get()));
        log.info("=================方法调用结束=================");
    }
}
