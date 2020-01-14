package com.demo.http.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @ControllerAdvice 表示这个是全局异常处理器，用于处理业务异常
 *
 * 配置springMVC的全局异常处理器
 * 当异常从产生点一直往上抛，最后Controller层继续往上抛，就有这里全局异常处理bean 来决定如何处理异常，并返回给前端
 * 工作请定好规范，抛异常要不要使用http的status code来标识；
 * 一般前后端分离的话，一般业务异常status code还是用200，返回体统一用 errorCode 来标识业务错误码（因为扩展性），参考微信公众号API
 *
 * 这里不能处理404/NoHandlerFoundException异常，因为不会经过这里
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @ExceptionHandler 该方法处理匹配哪些异常，如何处理这些异常，并返回给前端
     * 看@ExceptionHandler 的源码/注释就知道处理方法能写哪些参数，返回类型能写哪些
     * */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Object handleNullPointerException(NullPointerException exception) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errorCode", 100);
        returnMap.put("errorMessage", exception.getMessage());
        return returnMap;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public Object handleIllegalArgumentException(IllegalArgumentException exception) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errorCode", 101);
        returnMap.put("errorMessage", exception.getMessage());
        return returnMap;
    }

}
