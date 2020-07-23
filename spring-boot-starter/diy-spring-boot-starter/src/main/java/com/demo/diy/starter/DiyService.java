package com.demo.diy.starter;


/**
 自定义服务功能，什么自定义的 redisTemplate，mongoTemplate，缓存服务，日志服务 等等
 取决于自定义 starter 的什么功能组件
 下面就简单的写些方法
 * */
public class DiyService {

    private String name;
    private Integer age;

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

    public String diy(String s) {
        String string = "调用----" + this.getClass().getName() + ".diy方法----" + s;
        System.out.println(string);
        return string;
    }


}
