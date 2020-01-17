package com.demo.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * */
@Controller
public class RestfulAndNoRestfulController {

    /**
     * GET请求无法使用 application/json， 看看在url中的参数能不能带入 @RequestBody Map<String, Object> requestMap
     * 请求示例： 127.0.0.1:8080/restfulAndNoRestfulController/get1?name=cwm&age=18
     * 直接404，所以@GetMapping 无法与 @RequestBody 使用，因为url中的参数无法解析带入requestMap， 你可以试一下定义POJO看看
     *
     * 所以查询按Restful风格是GET请求，只能url传参，参数的数据结构只能是一维（可能不足，但应该足以应付大部分情况）
     * 如果参数数据结构需要json嵌套，那就改成POST请求嘛（虽然不遵循Restful风格，但是这种情况少，反正实现了功能了）
     * */
    @GetMapping(value = "/restfulAndNoRestfulController/get1")
    @ResponseBody
    public Object get1(@RequestBody Map<String, Object> requestMap) {
        return requestMap;
    }

    /**
     *
     * */
    @GetMapping(value = "/restfulAndNoRestfulController/get2")
    @ResponseBody
    public Object get2(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        responseMap.put("name", name);
        responseMap.put("age", age);
        return responseMap;
    }


}
