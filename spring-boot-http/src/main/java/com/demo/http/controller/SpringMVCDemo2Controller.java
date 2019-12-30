package com.demo.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 以下是 SpringMVC 的注解示例
 * 我不常用这些注解，其实功能一样，不过请约定/告知/强制 使用同一套规范/写法
 *
 * @RestController org.springframework.web.bind.annotation.RestController springMVC的注解,是组合注解，用于表示Rest风格的控制器
 * */
@RestController
public class SpringMVCDemo2Controller {

    /**
     * @GetMapping org.springframework.web.bind.annotation.GetMapping springMVC的注解，
     * 表示接受http GET方法请求，其他http请求方法你都猜得出来啊
     * */
    @GetMapping(value = "/springMVCAnnotationDemo2Controller/test1")
    public Object test1() {
        return new String("/springMVCAnnotationDemo2Controller/test1");
    }

    /**
     * @PostMapping org.springframework.web.bind.annotation.PostMapping springMVC的注解，
     * 表示接受http POST方法请求，其他http请求方法你都猜得出来啊
     * */
    @PostMapping(value = "/springMVCAnnotationDemo2Controller/test2")
    public Object test2() {
        return new String("/springMVCAnnotationDemo2Controller/test2");
    }

}
