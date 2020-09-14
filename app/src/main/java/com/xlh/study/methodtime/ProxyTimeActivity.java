package com.xlh.study.methodtime;

import android.os.Bundle;

import com.xlh.study.methodtime.annotation.ProxyTime;
import com.xlh.study.methodtime.dynamicproxy.IMethodTime;
import com.xlh.study.methodtime.dynamicproxy.MethodTimeHandler;

import java.lang.reflect.Proxy;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author: Watler Xu
 * time:2020/9/14
 * description:
 * version:0.0.1
 */
public class ProxyTimeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        IMethodTime proxy = (IMethodTime) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{IMethodTime.class}, new MethodTimeHandler(this));
        proxy.methodTime();

        method3();
        method4();
    }

    @ProxyTime
    private void method3() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @ProxyTime
    private void method4() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
