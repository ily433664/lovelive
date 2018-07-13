package com.lovelive.sys.aop;

import com.google.gson.Gson;
import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.service.ILogService;
import com.lovelive.sys.anno.LogAnnotation;
import com.lovelive.common.util.NetworkUtil;
import com.lovelive.user.entity.User;
import com.lovelive.user.util.UserUtils;
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
import java.util.Date;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: OperLogAopAction
 * @Description: AOP 日志记录
 * @date 2018年1月18日 下午5:57:20
 */

@Aspect
@Component
public class OperLogAopAction {

    private static final String SUCCESS_RESULT_CONTENT = "操作成功！";
    private static final Log log = LogFactory.getLog(OperLogAopAction.class);

    private ILogService logService;

    @Autowired
    public OperLogAopAction(ILogService logService) {
        this.logService = logService;
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
            OperationLog operLog = new OperationLog();// 创建日志对象
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
            operLog.setOperType(logAnnotation.mold());// 操作类型
            operLog.setOperFunction(logAnnotation.methods());// 操作功能
            operLog.setOperIP(NetworkUtil.getIpAddr(request));// IP地址
            operLog.setOperMethod(target.getClass().getName() + "." + methodName);// 操作方法
            operLog.setOperURI(request.getRequestURI());// operURI
            operLog.setOperParameter(new Gson().toJson(request.getParameterMap()));// 请求参数

            Date startTime = new Date();
            try {
                object = pjp.proceed();// 执行请求
                operLog.setIfSuccess(true);// 成功
                operLog.setResultContent(SUCCESS_RESULT_CONTENT);// 本次操作的结果
            } catch (Exception e) {
                operLog.setIfSuccess(false);// 失败
                operLog.setResultContent(e.toString());// 异常情况
                throw e;
            }

            Date endTime = new Date();
            operLog.setResponseTime(endTime.getTime() - startTime.getTime());// 响应时间

            User user = UserUtils.getUser();// 获取当前用户

            if (user != null) {
                operLog.setOperAccount(user.getAccount());// 用户账号
                operLog.setOperName(user.getUserName());// 用户名称

                StringBuilder operRole = new StringBuilder();
                if (user.getUserRoles() != null) {
                    user.getUserRoles().forEach(userRole -> {
                        operRole.append(userRole.getRole().getName()).append(",");
                    });
                    operLog.setOperRole(operRole.deleteCharAt(operRole.length() - 1).toString());// 用户角色
                }
            }
            logService.saveOperationLog(operLog);
        } else {
            object = pjp.proceed();// 执行请求
        }

        return object;
    }
}