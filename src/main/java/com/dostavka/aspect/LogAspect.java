package com.dostavka.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalTime;

@Aspect
@Component
public class LogAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("within(com.dostavka.*)")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Start doing method " + joinPoint.getSignature());
    }

    @After("within(com.dostavka.service.UserService)")
    public void logAfterMethod(JoinPoint joinPoint) {
        log.info("Finish doing method " + joinPoint.getSignature());
    }

    @AfterReturning("within(com.dostavka.service.UserService)")
    public void logAfterReturningMethod(JoinPoint joinPoint) {
        log.info("Log after returning " + joinPoint.getSignature());
    }

    @AfterThrowing(throwing = "error", value = "within(com.dostavka.service.UserService)")
    public void logAfterThrowingMethod(Throwable error) {
        log.info("We have error: " + error);
    }

    @Around("@annotation(com.dostavka.annotation.CheckTimeAnnotation)")
    public void logAfterAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        System.out.println("Timer start...");
        joinPoint.proceed();
        LocalTime end = LocalTime.now();
        System.out.println("Timer end...");
        log.info(String.valueOf(end.getNano() - start.getNano()));
    }
}