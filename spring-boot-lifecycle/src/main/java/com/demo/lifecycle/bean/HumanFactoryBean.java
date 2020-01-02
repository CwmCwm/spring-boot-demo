package com.demo.lifecycle.bean;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * org.springframework.beans.factory.FactoryBean FactoryBean是一个工厂Bean，可以生成某一个类型Bean实例
 * 该类命名为HumanFactoryBean 这里语义就是这是一个Human 的工厂Bean
 * 因为HumanFactoryBean 实现FactoryBean接口，所以HumanFactoryBean 的bean的name 是 &humanFactoryBean  多加了 & 符号
 *
 * 虽然有FactoryBean接口，但使用场景感觉不多，我也能用 @Bean 和 @Scope 来实现，就没必要这种写法
 * */
@Component
public class HumanFactoryBean implements FactoryBean<Human> {

    /**
     * springIOC容器就是调用工厂Bean的getObject() 来获取对应bean实例
     * */
    @Override
    public Human getObject() throws Exception {
        Integer age = new Random().nextInt(100);
        return new Human(age);
    }

    // 看接口注释
    @Override
    public Class<?> getObjectType() {
        return Human.class;
    }

    // 是否单例模式，自己实验一下，这里就在LifecycleApplication 写实验代码；
    @Override
    public boolean isSingleton() {
        //return true;
        return false;
    }

}
