package com.lovelive.sys.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 定时任务Aspect
 *
 * @author dHe
 */
@Aspect
@Component
public class TaskAspect {

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void annotationCut() {
    }

    @Around("annotationCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取系统参数，配置为 true 才执行定时任务
        if (Boolean.valueOf(System.getProperty("executeTask"))) {
            return pjp.proceed();
        }
        return null;
    }
}
