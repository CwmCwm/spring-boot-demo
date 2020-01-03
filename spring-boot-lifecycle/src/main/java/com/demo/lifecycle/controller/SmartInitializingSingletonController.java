package com.demo.lifecycle.controller;


import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Controller;


/**
 * org.springframework.beans.factory.SmartInitializingSingleton 单例bean初始化后会调用afterSingletonsInstantiated方法
 * 打个断点就能看到springIOC源码调用处
 * */
@Controller
public class SmartInitializingSingletonController implements SmartInitializingSingleton {

    /**
     *
     * */
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("SmartInitializingSingletonController.afterSingletonsInstantiated() => 在单例bean初始化后调用");
    }
}
