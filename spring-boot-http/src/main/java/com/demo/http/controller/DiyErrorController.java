package com.demo.http.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * spring-boot的默认实现 org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
 * 就是BasicErrorController 处理了 /error 这个url
 * 而404 NoHandlerFoundException 会内部重定向到 /error
 * 实现ErrorController接口来自定义 /error 如何处理，这里自定义 /error 去自定义返回
 * */
@Controller
public class DiyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
     * 这里因为返回格式规范，所以自定义返回格式
     * 如果有其他异常在 全局异常处理器 无法捕获处理的，springMVC会内部重定向到 /error （就是这里了），下面多写几个 if 完事
     * */
    @RequestMapping("/error")
    @ResponseBody
    public Object error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errorCode", 404);
        returnMap.put("errorMessage", "404错误，该url不存在");
        return returnMap;
    }

}
