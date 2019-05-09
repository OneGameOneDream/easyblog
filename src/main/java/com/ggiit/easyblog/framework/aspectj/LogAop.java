package com.ggiit.easyblog.framework.aspectj;

import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.common.constant.WebKeys;
import com.ggiit.easyblog.common.util.security.UserUtils;
import com.ggiit.easyblog.project.system.user.entity.User;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log syslog = method.getAnnotation(Log.class);
        //当前登陆用户
        User user = UserUtils.getUser();
        //用户角色
        List<String> roleNameList = new ArrayList<>();
        user.getAuthorities().forEach(role -> roleNameList.add(role.toString()));
        String roleArr[] = roleNameList.toArray(new String[roleNameList.size()]);
        //打印请求的内容
        //获取请求头中的User-Agent
        log.info("============================方法调用开始============================");
        log.info("方法描述：{}", syslog.value());
        log.info("请求用户：{}", user.getNickname());
        log.info("拥有角色：{}", Arrays.toString(roleArr));
        log.info("请求路径：{}", request.getRequestURL().toString());
        log.info("请求IP : {}", request.getRemoteAddr());
        log.info("请求类型：{}", request.getMethod());
        log.info("类方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 方法执行后
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        //处理完请求后，返回内容
        log.info("方法返回值：{}", ret);
        log.info("方法执行时间：{}毫秒", (System.currentTimeMillis() - startTime.get()));
        log.info("方法执行结果：{}", WebKeys.METHOD_SUCCESS);
        log.info("============================方法调用结束============================");
    }

    /**
     * 拦截异常操作
     *
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        // 记录本地异常日志
        log.error("方法调用异常");
        log.error("异常信息：{}", e.getMessage());
        log.info("方法执行结果：{}", WebKeys.METHOD_FAIL);
        log.info("============================方法调用结束============================");
    }
}
