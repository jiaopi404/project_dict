package com.jiaopi404.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * The type Aop service logger.
 */
@Slf4j
//@Component
//@Aspect
public class AopServiceLogger {

    /**
     * AOP 的通知类型
     * <p>1. 前置通知</p>
     * <p>2. 后置通知</p>
     * <p>3. 环绕通知 Around</p>
     * <p>3.1 execution 表达式格式，类似方法的定义：返回值 方法名（参数）</p>
     * <p>4. 异常通知</p>
     * <p>5. 最终通知</p>
     *
     * @param joinPoint the join point
     * @return the object
     * @throws Throwable the throwable
     */
    @Around(value = "execution(* com.jiaopi404.service.impl..*.*(..))")
    public Object serviceExecDurationLogger (ProceedingJoinPoint joinPoint) throws Throwable {
        // 计算时间
        log.info("[===正在执行方法:===]{}.{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
        Long startTime = System.currentTimeMillis(); // 当前时间毫秒数
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis(); // 结束后毫秒数
        Long timeSpan = endTime - startTime;
        String msg = "[===方法执行时间：===]" + joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName() + ": " + timeSpan;
        if (timeSpan > 3000) {
            log.error(msg);
        } else if (timeSpan > 2000) {
            log.warn(msg);
        } else {
            log.info(msg);
        }
        return result;
    }
}
