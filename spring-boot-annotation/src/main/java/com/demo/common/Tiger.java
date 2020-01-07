package com.demo.common;



public class Tiger {

    private String name;
    private Integer age = 18;

    public Tiger (String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
