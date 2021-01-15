package com.simbirsoft.springcourse.aop;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebControllerAspectLogger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SneakyThrows
    @Around("execution(* com.simbirsoft.springcourse.controller.EmployeeController.*(..))")
    public Object logController(ProceedingJoinPoint joinPoint){
        logger.info("[Logger is running]");
        logger.info("[Executed methods: {}]", joinPoint.getSignature().getName());
        logger.info("[Used arguments: {}]", Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        logger.info("[Logging stopped]");
        return result;
    }
}
