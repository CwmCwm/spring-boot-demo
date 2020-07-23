package com.demo.diy.starter;


import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 使用 @ConfigurationProperties(prefix = "diy")
 用来获取 applicant.properties中的配置信息，这个示例是 spring-boot-starter-main 引用 diy-spring-boot-starter ，
 所以就是获取 spring-boot-starter-main中applicant.properties的配置信息

 作用就是获取 applicant.properties中的配置信息 或 设置默认的配置值
 * */
@ConfigurationProperties(prefix = "diy")
public class DiyServiceProperties {

    // 如果在applicant.properties 中没有配置，就用"diy" 这个名字
    private String name = "diy";
    private Integer age = 18;


    // 下面这些getter和setter方便其他地方获取 name和age 数据
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


}
