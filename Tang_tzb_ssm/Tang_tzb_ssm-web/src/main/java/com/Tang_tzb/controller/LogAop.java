package com.Tang_tzb.controller;


import com.Tang_tzb.domain.SysLog;
import com.Tang_tzb.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    ISysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;
    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    @Before("execution(* com.Tang_tzb.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = joinPoint.getTarget().getClass();//具体访问的类

        String methodName = joinPoint.getSignature().getName(); //方法的名称
        Object[] args = joinPoint.getArgs();//获取方法的参数名称
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs);
        }
    }


    @After("execution(* com.Tang_tzb.controller.*.*(..))")
    public void doAfter() throws Exception {
        long time = new Date().getTime() - visitTime.getTime();

        String url = "";
        if (clazz != null || clazz != LogAop.class || method != null) {

            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);

            if (classAnotation != null){

                String[] classValue = classAnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = methodValue[0] + classValue[0];
                    String ip = request.getRemoteAddr();
                    //获取用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String userName = user.getUsername();

                    //将日志相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time); //执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(userName);
                    sysLog.setVisitTime(visitTime);

                    //调用Service完成操作
                    sysLogService.save(sysLog);

                }
            }
        }

    }

}
