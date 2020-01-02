package com.demo.lifecycle.bean;


/**
 * 定义一个类，用于演示这个Human如何实例化并如何springIOC容器中
 * 这里配合 HumanFactoryBean 一起演示
 * */
public class Human {

    // 写个属性用于多例bean的实验
    private Integer age;

    public Human (Integer age) {
        this.age = age;
    }

}
