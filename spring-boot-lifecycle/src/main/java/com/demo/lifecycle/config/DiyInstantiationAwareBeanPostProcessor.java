package com.demo.lifecycle.config;


import com.demo.lifecycle.LifecycleApplication;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;


/**
 * org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor
 * 而外定义在bean实例化前和bean实例化后生命节点调用
 *
 * org.springframework.beans.factory.config.BeanPostProcessor
 * 定义bean实例在初始化前和初始化后调用
 *
 * 这里测试就针对 LifecycleApplication
 */
@Component
public class DiyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    /**
     * InstantiationAwareBeanPostProcessor接口的方法
     * 在bean实例化前调用
     * 看InstantiationAwareBeanPostProcessor 注释
     * 这里需要返回Object哦，所以 Give BeanPostProcessors a chance to return a proxy instead of the target bean instance. 的语义就是
     * 这里要实例化bean，还要创建bean的代理类
     * */
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        if (beanName.equals("lifecycleApplication")) {
            System.out.println("DiyInstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation(Class<?> beanClass, String beanName) => 在bean实例化前这个生命节点" + beanClass);
        }
        return null;
    }

    /**
     * InstantiationAwareBeanPostProcessor接口的方法
     * 在bean实例化后调用
     * */
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifecycleApplication")) {
            System.out.println("DiyInstantiationAwareBeanPostProcessor.postProcessAfterInstantiation(Object bean, String beanName) => 在bean实例化后这个生命节点" + bean);
        }
        return true;
    }


    /**
     * BeanPostProcessor接口的方法
     * 在bean实例初始化前调用
     * */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifecycleApplication")) {
            System.out.println("DiyInstantiationAwareBeanPostProcessor.postProcessBeforeInitialization(Object bean, String beanName) => 在bean实例初始化前这个生命节点" + bean);
        }
        return bean;
    }


    /**
     * BeanPostProcessor接口的方法
     * 在bean实例初始化后调用
     * */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifecycleApplication")) {
            System.out.println("DiyInstantiationAwareBeanPostProcessor.postProcessAfterInitialization(Object bean, String beanName) => 在bean实例初始化后这个生命节点" + bean);
        }
        return bean;
    }

}
