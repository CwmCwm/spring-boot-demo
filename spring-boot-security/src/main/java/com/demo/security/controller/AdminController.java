package com.demo.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * 用于演示接口权限
 * */
@Controller
public class AdminController {

    @RequestMapping(value = "/admin/get")
    @ResponseBody
    public Object get() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "admin角色才有权限调用该接口，你就是admin角色");
        return responseMap;
    }

}
