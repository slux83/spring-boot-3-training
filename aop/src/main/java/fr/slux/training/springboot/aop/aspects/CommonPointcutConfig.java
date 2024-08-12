package fr.slux.training.springboot.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {

    @Pointcut("execution(* fr.slux.training.springboot.aop.*.*.*(..))")
    public void businessAndDataPackageConfig() {
    }

    //you can define pointcuts on bean names too
    @Pointcut("bean(*Service*)")
    public void allBeanServices() {
    }

    @Pointcut("@annotation(fr.slux.training.springboot.aop.annotations.TrackTime)")
    public void allAnnotationTrackTime() {
    }
}
