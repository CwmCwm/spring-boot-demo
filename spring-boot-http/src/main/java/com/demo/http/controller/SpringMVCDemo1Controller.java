package com.demo.http.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 以下是 SpringMVC 的注解示例
 * 我一般用这个示例
 *   原因一：使用最小注解，即使用的注解不是组合注解
 *   原因二：url方便查找，如@RequestMapping 的用法
 *   原因三：使用前后端完全分离，服务器端不会做html渲染，前端自行选型
 *
 * @Controller org.springframework.stereotype.Controller spring的注解，用于表示控制器
 * */
@Controller
public class SpringMVCDemo1Controller {

    /**
     * @RequestMapping org.springframework.web.bind.annotation.RequestMapping springMVC的注解，
     * 表示uri的映射，uri映射规则/格式 请定义好（原则就是可读性）
     *
     * @ResponseBody org.springframework.web.bind.annotation.ResponseBody springMVC的注解，
     * 表示http返回时如何处理数据格式，你返回各种java类试一下;浏览器F12看一下具体返回头和返回体
     * 关于@ResponseBody的属性自己看源代码，有注释   同理注解如何使用也是看源码/注释/实操
     * */
    @RequestMapping(value = "/springMVCAnnotationDemo1Controller/test1")
    @ResponseBody
    public Object test1() {
        //返回java.lang.String类
//        return new String("String");

        //返回java.util.Map
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errorCode", 0);
        returnMap.put("errorMessage", "错误信息");
        List<String> list = new ArrayList<>();
        list.add("String1");
        list.add("String2");
        returnMap.put("list", list);
        return returnMap;
    }

}
