package com.xlh.study.methodtime.aspectj;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author: Watler Xu
 * time:2020/9/14
 * description:
 * version:0.0.1
 */
@Aspect
public class TimeAspect {

    /**
     * 进行一个切点
     * 注解NetworkCheck的包名路径，此处建议Copy Reference
     */
    @Pointcut("execution(@com.xlh.study.methodtime.annotation.Time * *(..))")
    public void pointActionMethod() {

    }

    /**
     * 进行切面的处理
     * 填写切点的方法名，注意带上()
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("pointActionMethod()")
    public void methodTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Signature signature = proceedingJoinPoint.getSignature();
        String name = signature.toShortString();
        long time = System.currentTimeMillis();
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log.e("Aspect--methodTime",name + " cost " + (System.currentTimeMillis() - time));
    }


}
