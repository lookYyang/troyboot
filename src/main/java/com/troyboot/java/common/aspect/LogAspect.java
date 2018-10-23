package com.troyboot.java.common.aspect;

import com.troyboot.java.common.utils.*;
import com.troyboot.java.common.annotation.Log;
import com.troyboot.java.common.po.SysLog;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.common.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    SysLogService logService;


    @Pointcut("@annotation(com.troyboot.java.common.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            // 注解上的描述
            sysLog.setOperation(log.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
            sysLog.setParams(params);
        } catch (Exception e) {

        }
        // 获取request
        HttpServletRequest request = CommonUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(CommonUtils.getIpAddr(request));
        // 用户名
        SysUser currUser = ShiroUtils.getUser();
        if (null == currUser) {
            if (null != sysLog.getParams()) {
                sysLog.setUser_id(-1);
                sysLog.setUser_name(sysLog.getUser_name());
            } else {
                sysLog.setUser_id(-1);
                sysLog.setUser_name("获取用户信息为空");
            }
        } else {
            sysLog.setUser_id(ShiroUtils.getUser().getId());
            sysLog.setUser_name(ShiroUtils.getUser().getName());
        }
        sysLog.setTime((int) time);
        sysLog.setCreate_time(new Date());
        // 保存系统日志
        logService.save(sysLog);
    }
}
