package com.demo.lifecycle.config;


import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;


/**
 * org.springframework.context.ApplicationContextInitializer ApplicationContext初始化执行的ApplicationContextInitializer
 * org.springframework.core.Ordered 有多个ApplicationContextInitializer吧，那ApplicationContext初始化时就要指定这些ApplicationContextInitializer的执行顺序
 *
 * 要显示加入到SpringApplication，有几种写法，这里用application.properties配置的写法（原因：统一配置在一个文件）
 * */
public class DiyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println(DiyApplicationContextInitializer.class.getName() + ".initialize(ConfigurableApplicationContext applicationContext) => " + " 在ApplicationContext初始化的生命周期节点执行");
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
