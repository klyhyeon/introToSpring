package com.intro.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodStartLoggingAsepct {
    //@Before("execution(* *..*ServiceImpl.*(..))")
    //@AfterReturning(value = "execution(* *..*ServiceImpl.*(..)", returning = "user")
//    @AfterThrowing(value = "execution(* *..*ServiceImpl.*(..)", throwing = "e")
    //AfterThrowing은 특정 예외를 다른 예외 형태로 바꿔줘야할 때 유용합니다.
//    @Around("execution(* *..*ServiceImpl.*(..)")
    public void startLog(JoinPoint jp, DataAccessException e) {
        System.out.println("메서드 시작: " + jp.getSignature() + "반환값=" + user);
        throw new ApplicationException(e);
    }

    @Around("execution(* *..*ServiceImpl.*(..)")
    public Object log(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("메서드 시작: " + jp.getSignature());
        try {
            //대상 메서드 실행
            Object result = jp.proceed();
            System.out.println("메서드 정상 종료: " + jp.getSignature() + "반환값=" + result);
            return result;
        } catch (Exception e) {
            System.out.println("메서드 비정상 종료: " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }
}
