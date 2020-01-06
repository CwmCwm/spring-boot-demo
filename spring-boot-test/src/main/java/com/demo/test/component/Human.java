package com.demo.test.component;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Human {

    private String name;
    private Integer age;

    public Human() {
        this.name = "Human";
        this.age = new Random().nextInt(100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
