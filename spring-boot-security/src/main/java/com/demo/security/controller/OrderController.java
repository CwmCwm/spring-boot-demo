package com.demo.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * 订单接口，演示根据用户的角色对应的接口权限，是否有权限访问接口
 * */
@Controller
public class OrderController {

    /**
     *
     * */
    @RequestMapping(value = "/order/get")
    @ResponseBody
    public Object get() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        Map<String, Object> order = new HashMap<>();
        order.put("orderId", 1);
        order.put("price", 100.00);
        responseMap.put("order", order);
        return responseMap;
    }

}
