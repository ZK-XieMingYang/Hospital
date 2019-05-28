package com.qhit.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


@Component
@Aspect
public class LogInterceptor {
    @Before(value = "execution(* com.qhit.*.controller.*.*(..))")
    public void before(JoinPoint jp){
        String className = jp.getClass().getName();
        String methodName = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(new Date());
        System.out.println(time+"开始执行类名："+className+"下的方法："+methodName+",参数是："+args);
    }
    @AfterReturning(value = "execution(* com.qhit.*.controller.*.*(..))",returning = "result")
    public void afterruning(JoinPoint jp, java.lang.Object result){
        String className = jp.getClass().getName();
        String methodName = jp.getSignature().getName();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(new Date());
        System.out.println(time+"结束执行类名："+className+"下的方法："+methodName+"," +
                "返回值是："+result+"");
    }
}
