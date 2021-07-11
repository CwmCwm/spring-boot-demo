package com.demo.office.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 以下是 SpringMVC 的注解示例
 * 我一般用这个示例
 *   原因一：使用最小注解，即使用的注解不是组合注解
 *   原因二：url方便查找，如@RequestMapping 的用法
 *   原因三：使用前后端完全分离，服务器端不会做html渲染，前端自行选型
 *
 * @Controller org.springframework.stereotype.Controller spring的注解，用于表示控制器
 * */
@Controller
public class SpringMVCDemo1Controller {

    /**
     * @RequestMapping org.springframework.web.bind.annotation.RequestMapping springMVC的注解，
     * 表示uri的映射，uri映射规则/格式 请定义好（原则就是可读性）
     *
     * @ResponseBody org.springframework.web.bind.annotation.ResponseBody springMVC的注解，
     * 表示http返回时如何处理数据格式，你返回各种java类试一下;浏览器F12看一下具体返回头和返回体
     * 关于@ResponseBody的属性自己看源代码，有注释   同理注解如何使用也是看源码/注释/实操
     * */
    @RequestMapping(value = "/springMVCAnnotationDemo1Controller/test1")
    @ResponseBody
    public Object test1() {
        //返回java.lang.String类
//        return new String("String");

        //返回java.util.Map
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "错误信息");
        List<String> list = new ArrayList<>();
        list.add("String1");
        list.add("String2");
        responseMap.put("list", list);
        return responseMap;
    }


    /**
     * GET方法请求
     * Restful风格，语义表示请求资源，用于查询
     * GET方法参数是放在url中的，使用 @RequestParam 来获取url中的参数，看@RequestParam注释
     *
     * 请求示例 http://127.0.0.1:8080//springMVCAnnotationDemo1Controller/get?name=cwm&age=18
     * */
    @RequestMapping(value = "/springMVCAnnotationDemo1Controller/get", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestParam(name = "name", defaultValue = "spring", required = false) String name, @RequestParam(name = "age") Integer age) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "错误信息");
        // 如数据库查询/调用其他服务查询/各种查询，然后返回查询数据
        Map<String, Object> human = new HashMap<>();
        human.put("name", name);
        human.put("age", age);
        responseMap.put("human", human);
        return responseMap;
    }

    /**
     * POST方法请求
     * Restful风格，语义表示提交资源，用于新增
     * POST方法参数是放在请求体中
     *
     * 请求示例  Content-Type:"application/json", 请求内容 {"name": "cwm", "age": 18}
     * */
    @RequestMapping(value = "/springMVCAnnotationDemo1Controller/post", method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody Map<String, Object> requestMap) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "错误信息");
        // 如数据库新增/调用其他服务新增/各种新增，然后返回新增的结果
        responseMap.put("requestMap", requestMap);
        return responseMap;
    }

    /**
     * PUT方法请求
     * Restful风格，语义表示修改资源，用于更新
     * PUT方法参数是放在请求体中
     *
     * 请求示例  Content-Type:"application/json", 请求内容 {"name": "cwm", "age": 18}
     * */
    @RequestMapping(value = "/springMVCAnnotationDemo1Controller/put", method = RequestMethod.PUT)
    @ResponseBody
    public Object put(@RequestBody Map<String, Object> requestMap) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "错误信息");
        // 如数据库更新/调用其他服务更新/各种更新，然后返回更新的结果
        responseMap.put("requestMap", requestMap);
        return responseMap;
    }

    /**
     * DELETE方法请求
     * Restful风格，语义表示删除资源，用于删除
     * DELETE方法参数放哪？这里放在url中
     *
     * */
    @RequestMapping(value = "/springMVCAnnotationDemo1Controller/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "age", required = false) Integer age) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "错误信息");
        // 如数据库删除/调用其他服务删除/各种删除，然后返回删除的结果
        Map<String, Object> human = new HashMap<>();
        human.put("name", name);
        human.put("age", age);
        responseMap.put("human", human);
        return responseMap;
    }

}
