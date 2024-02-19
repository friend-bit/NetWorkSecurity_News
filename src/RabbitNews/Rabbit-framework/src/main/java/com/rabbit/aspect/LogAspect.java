package com.rabbit.aspect;


import com.alibaba.fastjson.JSON;
import com.rabbit.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.rabbit.annotation.SystemLog)")
    public void pt(){

    }

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handAfter(ret);
        } finally {
            log.info("=========End========="+System.lineSeparator());
        }
        return ret;
    }

    private void handAfter(Object ret) {
        log.info("Response               :{}", JSON.toJSONString(ret));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        SystemLog systemLog=getSystemLog(joinPoint);

        log.info("=========Start=========");
        log.info("URL                    :{}",request.getRequestURL());
        log.info("BusinessName           :{}",systemLog.businessName());
        log.info("HTTP Method            :{}",request.getMethod());
        log.info("Class Method        :{}.{}",joinPoint.getSignature().getDeclaringTypeName(),((MethodSignature)joinPoint.getSignature()).getName());
        log.info("IP                     :{}",request.getRemoteHost());
        log.info("Request Args           :{}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }
}
