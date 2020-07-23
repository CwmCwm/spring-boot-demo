package com.demo.starter.main.controller;

import com.demo.diy.starter.DiyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 可以debug 看一下
 * */
@Controller
public class TestController {

    @Autowired
    private DiyService diyService;

    @RequestMapping(value = "/test/test1")
    @ResponseBody
    public Object test1() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("errorMessage", "错误信息");
        responseMap.put("name", diyService.getName());
        responseMap.put("age", diyService.getAge());
        responseMap.put("diy", diyService.diy(this.getClass().getName()));
        return responseMap;
    }

}
