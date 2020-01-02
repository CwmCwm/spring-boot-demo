package com.demo.lifecycle.bean;


public class Tiger {

    // 写个属性用于多例bean的实验
    private Integer age;

    public Tiger(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // 实例初始化方法，方法命名就用 init()
    public void init() {
       System.out.println(Tiger.class.getName() + ".init() => 实例初始化");
    }

    // 实例销毁方法，方法命名就用 destroy()
    public void destroy() {
        System.out.println(Tiger.class.getName() + ".destroy() => 实例销毁");
    }

}
