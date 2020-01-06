package com.demo.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用于演示spring-boot的Controller单元测试
 * */
@Controller
public class SpringMVCDemo1Controller {

    /**
     * GET请求
     * */
    @RequestMapping(value = "/springMVCAnnotationDemo1Controller/get", method = RequestMethod.GET)
    @ResponseBody
    public Object get() {
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
