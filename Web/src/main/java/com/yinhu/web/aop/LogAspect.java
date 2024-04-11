package com.yinhu.web.aop;


import com.alibaba.fastjson.JSONObject;
import com.yinhu.web.mappers.OperateLogMapper;
import com.yinhu.web.pojo.OperateLog;
import com.yinhu.web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect // 这是一个切面类
public class LogAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 环绕通知 用来记录日志 基于注解切入
     * @return
     */
    @Around("@annotation(com.yinhu.web.anno.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取日志表记录要素
        String token = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");
        LocalDateTime operateTime = LocalDateTime.now();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        long begin = System.currentTimeMillis();
        // 调用原始目标方法运行
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        String returnValue = JSONObject.toJSONString(result);
        Long costTime = end - begin;
        // 记录操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLog.setOperateUser(operateUser);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录操作日志: {}", operateLog);
        return result;
    }
}
