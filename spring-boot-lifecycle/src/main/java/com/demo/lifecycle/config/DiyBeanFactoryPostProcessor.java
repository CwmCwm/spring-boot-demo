package com.demo.lifecycle.config;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


/**
 * org.springframework.beans.factory.config.BeanFactoryPostProcessor 对BeanFactory的后置处理器
 * 在org.springframework.context.support.PostProcessorRegistrationDelegate的181行->invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory); 执行，debug看看
 */
@Component
public class DiyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(DiyBeanFactoryPostProcessor.class.getName() + ".postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) => 在BeanFactory的后置处理生命周期执行");
    }
}
