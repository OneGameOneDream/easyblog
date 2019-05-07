package com.ggiit.easyblog.framework.aspectj;


import com.ggiit.easyblog.common.constant.ResponseCode;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 统一封装返回值和异常处理
 */
@Slf4j
@Aspect
@Component
public class ResponseAop {
    /**
     * 切点
     */
    @Pointcut("execution(public * com.ggiit.easyblog.project.system.*.controller..*(..))")
    public void httpResponse() {

    }


    /**
     * 环切
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("httpResponse()")
    public ApiResult handlerController(ProceedingJoinPoint proceedingJoinPoint) {
        ApiResult apiResult = new ApiResult();
        try {
            //获取方法的执行结果
            Object proceed = proceedingJoinPoint.proceed();
            //如果方法的执行结果是ApiResult，则将该对象直接返回
            if (proceed instanceof ApiResult) {
                apiResult = (ApiResult) proceed;
            } else {
                //否则，就要封装到ApiResult的data中
                apiResult.setValue(proceed);
            }
        } catch (Throwable throwable) {
            //如果出现了异常，调用异常处理方法将错误信息封装到ApiResult中并返回
            apiResult = handlerException(throwable);
        }
        return apiResult;
    }

    /**
     * 异常处理
     */
    private ApiResult handlerException(Throwable throwable) {
        ApiResult apiResult = new ApiResult();
        //这里需要注意，返回枚举类中的枚举在写的时候应该和异常的名称相对应，以便动态的获取异常代码和异常信息
        //获取异常名称的方法
        String errorName = throwable.toString();
        errorName = errorName.substring(errorName.lastIndexOf(".") + 1);
        //直接获取properties文件中的内容
        apiResult.setMessage(ResponseCode.valueOf(errorName).getMsg());
        apiResult.setCode(ResponseCode.valueOf(errorName).getCode());
        return apiResult;
    }


}
