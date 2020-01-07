package com.demo.lifecycle;


import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 实现org.springframework.boot.CommandLineRunner接口，会在spring-boot启动完成后，调用该方法
 *
 * spring源码  org.springframework.boot.SpringApplication->322行: callRunners(context, applicationArguments);
 * */
@Component
@Order
public class DiyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>spring-boot启动后，再执行DiyCommandLineRunner.run(String... args)");
    }

}
