package com.demo.lifecycle;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 实现org.springframework.boot.ApplicationRunner接口，会在spring-boot启动完成后，调用该方法
 * 与org.springframework.boot.CommandLineRunner接口类似
 *
 * spring源码  org.springframework.boot.SpringApplication->322行: callRunners(context, applicationArguments);
 * */
@Component
@Order
public class DiyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>spring-boot启动后，再执行DiyApplicationRunner.run()执行了");
    }

}
