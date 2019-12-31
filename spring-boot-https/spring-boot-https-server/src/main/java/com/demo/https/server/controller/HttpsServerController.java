package com.demo.https.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class HttpsServerController {

    @RequestMapping(value = "/httpsServerController/test1")
    @ResponseBody
    public Object test1() {
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
