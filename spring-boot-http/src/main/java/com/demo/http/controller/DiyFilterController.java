package com.demo.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * DiyFilter测试使用
 * */
@Controller
public class DiyFilterController {

    @RequestMapping(value = "/diyFilterController/test1")
    @ResponseBody
    public Object test1() {
        return new String("/diyFilterController/test1");
    }

}
