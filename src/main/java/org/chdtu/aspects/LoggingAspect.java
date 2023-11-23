package org.chdtu.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* getName*(..))")
    public void pointcutName() {
    }

    @Pointcut("execution(* org.chdtu.Toyota.*(..))")
    public void pointcutToyota() {
    }

    @Pointcut("execution(* org.chdtu.Car.getPrice*(..))")
    public void pointcutCarPrice() {
    }

    @Pointcut("execution(* org.chdtu.Car.get*(..))")
    public void pointcutCar() {
    }

    @Before("pointcutName() && !pointcutCar()")
    public void beforeNameNotCar() {
        System.out.println(" ***** getName method ****");
    }
    @Before("pointcutToyota()")
    public void beforeGetToyota() {
        System.out.println(" *** getToyota method ****");
    }
    @Before("pointcutCarPrice() && !pointcutToyota()")
    public void logMethodCall2() {
        System.out.println(" ******** getPrice method ****");
    }
}