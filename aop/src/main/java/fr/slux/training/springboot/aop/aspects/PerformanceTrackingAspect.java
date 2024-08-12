package fr.slux.training.springboot.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class PerformanceTrackingAspect {
    public static final Logger LOG = LoggerFactory.getLogger(PerformanceTrackingAspect.class);

    // @Around("execution(* fr.slux.training.springboot.aop.*.*.*(..))")
    // Using custom annotations + pointcut is nice because you can attach the custom annotation to the methods you want
    @Around("fr.slux.training.springboot.aop.aspects.CommonPointcutConfig.allAnnotationTrackTime()")
    public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object returnedVal = proceedingJoinPoint.proceed();

        long stopTime = System.currentTimeMillis();
        long execDuration = stopTime - startTime;

        LOG.info("Performance Aspect - Method {} executed in {} milli sec(s)",
                proceedingJoinPoint.getSignature(), execDuration);
        return returnedVal;
    }

}
