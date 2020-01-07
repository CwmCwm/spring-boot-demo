package com.demo.lifecycle.configuration;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


/**
 * 使用时，实现SpringApplicationRunListener接口后，要在 resources/META-INF/spring.factories 中配置，spring-boot才会实例化该类
 * 接口的方法命名就是springIOC容器初始化时对应的流程
 * 观察下面的执行顺序
 */
public class DiySpringApplicationRunListener implements SpringApplicationRunListener {

    public DiySpringApplicationRunListener(SpringApplication application, String[] args) {
    }

    @Override
    public void starting() {
        System.out.println("DiySpringApplicationRunListener.starting()执行了");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("DiySpringApplicationRunListener.environmentPrepared()执行了");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("DiySpringApplicationRunListener.contextPrepared()执行了");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("DiySpringApplicationRunListener.contextLoaded()执行了");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("DiySpringApplicationRunListener.started()执行了");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("DiySpringApplicationRunListener.running()执行了");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("DiySpringApplicationRunListener.failed()执行了");
    }


}
