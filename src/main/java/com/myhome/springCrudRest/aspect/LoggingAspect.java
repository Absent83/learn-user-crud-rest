package com.myhome.springCrudRest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */
@Component
@Aspect
public class LoggingAspect {
    private final static Logger log = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* com.myhome.springCrudRest.service.*.*(..))")
    public void serviceMethod() {
    }

    @Around("serviceMethod()")
    public Object logDbServiceCall(ProceedingJoinPoint thisJoinPoint) {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();

        System.out.println("xxx");

        log.info("Call method " + methodName + " with args " + methodArgs);
        Object result = null;

        try {
            result = thisJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        log.info("Method " + methodName + " returns " + result);
        return result;
    }
}