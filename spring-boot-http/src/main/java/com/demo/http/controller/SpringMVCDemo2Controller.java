package com.demo.http.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 以下是 SpringMVC 的注解示例
 * 我不常用这些注解，其实功能一样，不过请约定/告知/强制 使用同一套规范/写法
 *
 * @RestController org.springframework.web.bind.annotation.RestController springMVC的注解,是组合注解，用于表示Rest风格的控制器
 * */
@RestController
public class SpringMVCDemo2Controller {

    /**
     * spring-boot默认使用 com.fasterxml.jackson 作为json的序列化和反序列化工具，这里直接注入spring-boot自动注册的bean
     * 不用自己写bean注入 或者 自己写工具类
     * */
    @Autowired
    private ObjectMapper objectMapper;


    /**
     * @GetMapping org.springframework.web.bind.annotation.GetMapping springMVC的注解，
     * 表示接受http GET方法请求，其他http请求方法你都猜得出来啊
     * */
    @GetMapping(value = "/springMVCAnnotationDemo2Controller/test1")
    public Object test1() {
        return new String("/springMVCAnnotationDemo2Controller/test1");
    }

    /**
     * @PostMapping org.springframework.web.bind.annotation.PostMapping springMVC的注解，
     * 表示接受http POST方法请求，其他http请求方法你都猜得出来啊
     *
     请求示例 127.0.0.1:8080/springMVCAnnotationDemo2Controller/test2
     Content-Type:application/json
    {
        "name": "cwm",
        "age": 18
    }
     * */
    @PostMapping(value = "/springMVCAnnotationDemo2Controller/test2")
    public Object test2(@RequestBody Map<String, Object> requestMap) throws JsonProcessingException {
        // 深拷贝的常用方法，序列化和反序列化
        Map responseMap = objectMapper.readValue(objectMapper.writeValueAsString(requestMap), Map.class);
        return responseMap;
    }

}
