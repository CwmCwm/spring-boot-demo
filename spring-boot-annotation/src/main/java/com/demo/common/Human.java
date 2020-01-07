package com.demo.common;

import org.springframework.stereotype.Component;


/**
 * 在这个项目中，main类 com.demo.annotation.AnnotationApplication 默认是不会扫描到该组件的
 * 需要通过 @ComponentScan 手动配置扫描到该路径 com.demo.common
 * */
@Component
public class Human {

    private Integer age = 18;

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                '}';
    }
}
