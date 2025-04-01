package com.licensepro.jurisdictions.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class RestLoggingAspect {

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void restControllerPointcut() {
  }

  @Before("restControllerPointcut()")
  public void logBeforeRequest(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String methodName = signature.getMethod().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();

    log.info("REST Request: {}.{}() with arguments: {}", className, methodName, Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(value = "restControllerPointcut()", returning = "result")
  public void logAfterRequest(JoinPoint joinPoint, Object result) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String methodName = signature.getMethod().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();

    log.info("REST Response: {}.{}()", className, methodName);
    // logger.info("REST Response: {}.{}() returned: {}", className, methodName, result);
  }

  @AfterThrowing(value = "restControllerPointcut()", throwing = "exception")
  public void logAfterException(JoinPoint joinPoint, Throwable exception) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String methodName = signature.getMethod().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();

    // log with stacktrace
    log.error(String.format("REST Exception in %s.%s(): %s", className, methodName, exception.getMessage()), exception);
  }
}
