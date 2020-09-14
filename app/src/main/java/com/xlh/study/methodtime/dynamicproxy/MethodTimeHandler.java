package com.xlh.study.methodtime.dynamicproxy;

import android.util.Log;

import com.xlh.study.methodtime.annotation.ProxyTime;
import com.xlh.study.methodtime.annotation.Time;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Watler Xu
 * time:2020/9/14
 * description:
 * version:0.0.1
 */
public class MethodTimeHandler implements InvocationHandler {

    private Object target;

    public MethodTimeHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {

        Method[] declaredMethods = target.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            ProxyTime annotation = declaredMethod.getAnnotation(ProxyTime.class);
            if(annotation!=null){
                long start = System.currentTimeMillis();

                try {
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(target,objects);
                    long end = System.currentTimeMillis();
                    long cost = end - start;
                    Log.e("Proxy--methodTime",declaredMethod.getName() + " cost " + cost);
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }

        return null;
    }
}
