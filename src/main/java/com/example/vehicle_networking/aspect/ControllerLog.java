package com.example.vehicle_networking.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/3/25 21:15
 */
@Aspect
@Component
@Slf4j
public class ControllerLog {

    @Pointcut("execution(public * com.example.vehicle_networking.controller.*.*(..))")
    public void controller() {
    }

    @Before("controller()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getDeclaringTypeName() + "." + signature.getName();
        log.info("-----------------------------------------------------");
        log.info("Running methods:  " + method);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("args: " + arg);
        }
    }

}
