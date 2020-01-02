package com.demo.lifecycle.controller;


import com.demo.lifecycle.bean.Tiger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * 实验 Tiger bean的 @Scope(value = "request") 和 @Bean(initMethod = "init", destroyMethod = "destroy")
 *
 * */
// @Scope(value = "request")  debug看调用栈就知道当前的TigerController对应的bean是多例模式，作用域是每次http请求会实例化一个新的bean
// @Scope(value = "session")  debug看调用栈就知道当前的TigerController对应的bean是多例模式，作用域是每个session会对应同一个bean（用两个浏览器测试）
@Scope(value = "session")
@Controller
public class TigerController {

    @Autowired
    Tiger tiger;

    @RequestMapping(value = "/tigerController/test1")
    @ResponseBody
    public Object test1() {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errorCode", 0);
        returnMap.put("errorMessage", "success");
        Map<String, Object> tigerMap = new HashMap<>();
        tigerMap.put("age", tiger.getAge());
        returnMap.put("tiger", tigerMap);
        return returnMap;
    }

}
