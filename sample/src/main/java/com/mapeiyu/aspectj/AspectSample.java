package com.mapeiyu.aspectj;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;

//@Aspect
public class AspectSample {

//    @Around("call(* java.lang.Throwable+.printStackTrace())")
//    public Object logAndExecute(ProceedingJoinPoint joinPoint, ProceedingJoinPoint.EnclosingStaticPart enclosePart) throws Throwable {
//        System.err.println(enclosePart.getSignature() + ":" + joinPoint.getSourceLocation());
//        if (BuildConfig.DEBUG) {
//            return joinPoint.proceed();
//        } else {
//            return null;
//        }
//    }
//
//    @Before("handler(java.lang.Throwable+)&& args(e)")
//    public void log(JoinPoint joinPoint, Throwable e) {
//        System.err.println(e.toString());
//    }

}
