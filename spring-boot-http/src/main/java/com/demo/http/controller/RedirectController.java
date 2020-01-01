package com.demo.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * springMVC重定向，注意重定向的使用场景，如用户收藏主页url，而你改变了主页url，你要兼容跳转咯；
 * 重定向有两种实现，第一种在服务器内部重定向，第二种在浏览器上重定向
 * 观察处理的线程，那么请求携带参数如何传参
 * GET方法没有问题，参数在拼接在url中
 * POST方法本来就该避免重定向问题
 * */
@Controller
public class RedirectController {

    /**
     * 重定向后的url，用于重定向演示使用
     * */
    @RequestMapping(value = "/redirectController/test1")
    @ResponseBody
    public String test1() {
        System.out.println("/redirectController/test1" + " -> " + Thread.currentThread());
        return "RedirectController";
    }

    /**
     * 第一种在服务器内部重定向，结合重定向的使用场景，不推荐这种
     * 浏览器访问 http://127.0.0.1:8080/redirectController/forward 看看请求的url有变吗？
     * */
    @RequestMapping(value = "/redirectController/forward")
    public String forward() {
        System.out.println("/redirectController/forward" + " -> " + Thread.currentThread());
        return "forward:/redirectController/test1";
    }

    /**
     * 第二种在浏览器上重定向，结合重定向的使用场景，推荐这种
     * 浏览器访问 http://127.0.0.1:8080/redirectController/redirect 看看请求的url有变吗？
     * */
    @RequestMapping(value = "/redirectController/redirect")
    public String redirect() {
        System.out.println("/redirectController/redirect" + " -> " + Thread.currentThread());
        return "redirect:/redirectController/test1";
    }



}
