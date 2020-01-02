package com.demo.lifecycle.controller;


import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;


/**
 * org.springframework.beans.factory.SmartInitializingSingleton 单例bean初始化后会调用afterSingletonsInstantiated方法
 * spring源码见
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
