package com.yinhu.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Slf4j
@Aspect
public class MyAspect {

    // 抽取切入点表达式
    @Pointcut("execution(* com.yinhu.web.service.impl.DeptServiceImpl.*(..))")
    private void pt(){}; // 如果要让外部切面类访问 设为public

    @Pointcut("@annotation(com.yinhu.web.aop.MyTag)")
    private void pt2(){};


    /**
     *  // @Around 需要自己调用proceedingJoinPoint.proceed()执行原始方法 其他通知不需要考虑目标方法执行
     *  // 且Around环绕通知的方法必须指定返回值为Object 接收原始方法的返回值
     * @param proceedingJoinPoint 连接点对象
     * @return 返回目标方法的返回值
     * @throws Throwable 抛出目标方法可能存在的异常
     */
    @Around("pt()") // *(..)指的是任意方法
    public Object aroundAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("=============================");
        Object result = proceedingJoinPoint.proceed(); // 将原始方法运行结果返回 否则拿不到返回值
        log.info("=============================");
        return result;
    }

    @Before("execution(* com.yinhu.web.service.impl.DeptServiceImpl.*(..))")
    public void beforeAspect(JoinPoint joinPoint){
        // 如果是环绕通知 形式参数 为ProceedingJoinPoint类型
        // 其余四种通知的形式参数类型为JoinPoint 为环绕通知的父类
        log.info("我是前置通知 我来解析目标对象");
        String clsName = joinPoint.getTarget().getClass().getName(); // 获取目标对象的类名
        String methodName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();
        // 获取目标对象的返回值类型需要在环绕通知中获取！
        log.info("目标对象的类名为 {} 方法名为 {} 传入参数为{}", clsName, methodName, params);

    }

    @After("execution(* com.yinhu.web.service.impl.DeptServiceImpl.*(..))")
    public void afterAspect(){
        log.info("我是最终通知 我一定会执行");
    }

    /**
     * AfterReturning和AfterThrowing是互斥的
     */
    @AfterReturning("execution(* com.yinhu.web.service.impl.DeptServiceImpl.*(..))")
    public void afterReturningAspect(){
        log.info("我是正确返回无异常通知");
    }

    @AfterThrowing("execution(* com.yinhu.web.service.impl.DeptServiceImpl.*(..))")
    public void afterThrowingAspect(){
        log.info("我是异常通知");
    }

    @Before("pt2()")
    public void beforeAspectUsingAnnotationPointCut(){
        log.info("我是根据注解作为切入点的前置通知");
    }
}
