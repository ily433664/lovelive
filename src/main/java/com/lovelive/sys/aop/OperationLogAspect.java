package com.lovelive.sys.aop;

import com.google.gson.Gson;
import com.lovelive.common.uitls.NetworkUtils;
import com.lovelive.sys.anno.LogAnnotation;
import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.entity.User;
import com.lovelive.sys.service.IOperationLogService;
import com.lovelive.sys.utils.UserUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * AOP 日志记录
 *
 * @author dHe
 * @date 2018-01-18
 */
@Aspect
@Component
public class OperationLogAspect {

    private static final String SUCCESS_RESULT_CONTENT = "操作成功！";
    private static final Log log = LogFactory.getLog(OperationLogAspect.class);

    private IOperationLogService operationLogService;

    @Autowired
    public OperationLogAspect(IOperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    // 配置接入点
    @Pointcut("execution(* com.lovelive.*.web.*.*(..))")
    private void controllerAspect() {

    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        Object object;

        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称，当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的放参数类型
        MethodSignature msig;
        Method method = null;
        try {
            msig = (MethodSignature) pjp.getSignature();
            method = target.getClass().getMethod(methodName, msig.getMethod().getParameterTypes());
        } catch (Exception e) {
            log.error(e);
        }

        // 判断是否包含自定义的注解
        if (null != method && method.isAnnotationPresent(LogAnnotation.class)) {
            OperationLog operationLog = new OperationLog();// 创建日志对象
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
            operationLog.setOperType(logAnnotation.mold().getCode());// 操作类型
            operationLog.setOperFunction(logAnnotation.description());// 操作功能
            operationLog.setOperIP(NetworkUtils.getIpAddress(request));// IP地址
            operationLog.setOperMethod(target.getClass().getName() + "." + methodName);// 操作方法
            operationLog.setOperURI(request.getRequestURI());// operURI
            operationLog.setOperParameter(new Gson().toJson(request.getParameterMap()));// 请求参数
            operationLog.setUserAgent(request.getHeader("user-agent"));// 代理

            Long startTime = Calendar.getInstance().getTimeInMillis();
            try {
                object = pjp.proceed();// 执行请求
                operationLog.setSuccessed(true);// 成功
                operationLog.setResultContent(SUCCESS_RESULT_CONTENT);// 本次操作的结果
            } catch (Exception e) {
                operationLog.setSuccessed(false);// 失败
                operationLog.setResultContent(e.toString());// 异常情况
                throw e;
            }
            Long endTime = Calendar.getInstance().getTimeInMillis();
            operationLog.setResponseTime(endTime - startTime);// 响应时间

            User user = UserUtils.getUser();// 获取当前用户

            if (user != null) {
                operationLog.setOperAccount(user.getAccount());// 用户账号
                operationLog.setOperName(user.getUsername());// 用户名称

                StringBuilder operRole = new StringBuilder();
                if (user.getUserRoles() != null) {
                    user.getUserRoles().forEach(userRole -> {
                        operRole.append(userRole.getRole().getName()).append(",");
                    });
                    if (operRole.length() > 0) {
                        operationLog.setOperRole(operRole.deleteCharAt(operRole.length() - 1).toString());// 用户角色
                    }
                }
            }
            operationLogService.saveOperationLog(operationLog);
        } else {
            object = pjp.proceed();// 执行请求
        }

        return object;
    }
}