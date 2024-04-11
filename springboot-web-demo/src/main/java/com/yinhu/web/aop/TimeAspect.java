package com.yinhu.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect // AOP类
public class TimeAspect {

    @Around("execution(* com.yinhu.web.service.*.*(..))") //切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始事件
        long startTime = System.currentTimeMillis();
        // 使用原始方法运行
        Object result = joinPoint.proceed();
        // 记录结束时间 计算方法执行耗时
        long endTime = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法执行耗时: {}ms", endTime - startTime);
        return result;
    }
}
