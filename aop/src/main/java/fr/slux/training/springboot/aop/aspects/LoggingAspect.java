package fr.slux.training.springboot.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

// Configuration class with AOP
@Configuration
@Aspect
public class LoggingAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect() {
        LOG.warn("Creating aspect");
    }

    // Pointcut -> When? -> This is the execution(...)
    // syntax: (* PACKAGE.*.*(..)) all calls in the package, PACKAGE
    // Aspect = Advice + Pointcut => @Before @After @Around ...
    // Weaver = AOP framework
    // In this case we use a configuration pointcut as best practice so that if we rename the package we don't need to change everything around
    @Before("fr.slux.training.springboot.aop.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")
    public void logBeforeMethodCall(JoinPoint joinPoint) {
        // Called "Advice" in AOP
        LOG.info("Before Aspect - Method is called - {} with args {}",
                joinPoint.getSignature(), joinPoint.getArgs());
    }

    @After("execution(* fr.slux.training.springboot.aop.*.*.*(..))")
    public void logAfterMethodCall(JoinPoint joinPoint) {
        // Called "Advice" in AOP
        LOG.info("After Aspect - Method has executed - {} with args {}",
                joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterThrowing(
            pointcut = "execution(* fr.slux.training.springboot.aop.*.*.*(..))",
            throwing = "exceptionThrown")
    public void logAfterThrowingMethodCall(JoinPoint joinPoint, Exception exceptionThrown) {
        // Called "Advice" in AOP
        LOG.info("After Exception Aspect - Method has executed - {} with args {} - Exception: {}",
                joinPoint.getSignature(), joinPoint.getArgs(), exceptionThrown.toString());
    }

    @AfterReturning(
            pointcut = "execution(* fr.slux.training.springboot.aop.*.*.*(..))",
            returning = "returningVal")
    public void logAfterReturningMethodCall(JoinPoint joinPoint, Object returningVal) {
        // Called "Advice" in AOP
        LOG.info("After Returning Aspect - Method has executed - {} with args {} - Returned: {}",
                joinPoint.getSignature(), joinPoint.getArgs(), returningVal);
    }
}
