package com.bootdo.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by Lihq on 2018/9/29 16:11
 * Description: 切面
 */
@Component
@Aspect     // 1. 标注切面类
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 2. 定义切点 .. 子包, .* 所有类, .*(..) 所有方法
     */
    @Pointcut("execution(* com.bootdo..controller.*.*(..))")
    public void logPointcut() {}

    /**
     * 前置监听
     * @param point
     */
    @Before(value = "logPointcut()")
    public void doBefore(JoinPoint point) {
        // 接收到请求，记录请求内容

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("HTTP_URL: "+request.getRequestURL());
        logger.info("METHOD_TYPE: "+request.getMethod());
      //  logger.info("真实地址: "+ IPAddressUtil.g);
        logger.info("CLASS: "+point.getSignature().getDeclaringTypeName());
        logger.info("METHOD: "+point.getSignature().getName());
        logger.info("REQUEST_ARGS: "+ Arrays.toString(point.getArgs()));
    }

    /**
     * 环绕通知
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        logger.info("USER TIME:" + (System.currentTimeMillis() - startTime));
        return obj;
    }

    /**
     * 后置通知
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPointcut()")
    public void doAfterReturning(Object ret) {
        logger.info("RESULT VALUE:" + ret);
    }
}
