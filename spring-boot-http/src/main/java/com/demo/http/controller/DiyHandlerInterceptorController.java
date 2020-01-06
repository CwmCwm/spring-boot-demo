package com.demo.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * DiyHandlerInterceptor测试使用
 * */
@Controller
public class DiyHandlerInterceptorController {

    @RequestMapping(value = "/diyHandlerInterceptorController/test1")
    @ResponseBody
    public Object test1() {
        return new String("/diyHandlerInterceptorController/test1");
    }

}
