package com.demo.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * springMVC统一异常处理器实操
 * 看 GlobalExceptionHandler 中定义处理异常的方法
 * */
@Controller
public class ExceptionController {

    @RequestMapping(value = "/exceptionController/nullPointerException")
    @ResponseBody
    public Object nullPointerException() {
        throw new NullPointerException("空指针异常");
    }

    @RequestMapping(value = "/exceptionController/illegalArgumentException")
    @ResponseBody
    public Object illegalArgumentException() {
        throw new IllegalArgumentException("非法参数异常");
    }

}
