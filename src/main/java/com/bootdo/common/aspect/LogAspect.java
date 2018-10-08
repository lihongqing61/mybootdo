package com.bootdo.common.aspect;

import com.alibaba.druid.support.json.JSONUtils;
import com.bootdo.common.annonation.Log;
import com.bootdo.common.domain.LogDO;
import com.bootdo.common.service.LogService;
import com.bootdo.common.utils.HttpContextUtils;
import com.bootdo.common.utils.IPUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Lihq on 2018/10/8 13:44
 * Description: 日志切面
 */

@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogService logService;


    @Pointcut("@annotation(com.bootdo.common.annonation.Log)")
    public void logPointCut() {}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        long userTime = System.currentTimeMillis() - startTime;
        saveLog(point, userTime);
        return obj;
    }

    private void saveLog(ProceedingJoinPoint point, long userTime) {
        MethodSignature methodSignature = (org.aspectj.lang.reflect.MethodSignature) point.getSignature();
        LogDO log = new LogDO();
        Method method = methodSignature.getMethod();
        Log sysLog = method.getAnnotation(Log.class);
        if (sysLog != null) {
            log.setOperation(sysLog.value());   // 注解上的描述
        }

        // 请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        log.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = point.getArgs();

        try {
            String params = JSONUtils.toJSONString(args[0]).substring(0, 4999);
            log.setParams(params);
        } catch (Exception e) {

        }

        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        log.setIp(IPUtils.getIpAddr(request));

        // 用户名
        UserDO currUser = ShiroUtils.getUser();
        if (null == currUser) {
            if (null != log.getParams()) {
                log.setUserId(-1L);
                log.setUsername(log.getParams());
            } else {
                log.setUserId(-1L);
                log.setUsername("获取用户信息为空");
            }
        } else {
            log.setUserId(ShiroUtils.getUserId());
            log.setUsername(ShiroUtils.getUser().getUsername());
        }
        log.setTime((int) userTime);
        // 系统当前时间
        log.setGmtCreate(new Date());
        // 保存系统日志
        logService.save(log);
    }
}
