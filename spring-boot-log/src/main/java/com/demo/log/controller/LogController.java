package com.demo.log.controller;


import com.demo.log.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;



@Controller
public class LogController {

    /**
     * 面向接口编程的思想
     * spring-boot默认使用slf4j日志门面，注意引入的类是 org.slf4j.Logger
     * */
    private final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    LogService logService;

    @RequestMapping(value = "/logController/log", method = RequestMethod.GET)
    @ResponseBody
    public Object log() {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errorCode", 0);
        returnMap.put("errorMessage", "错误信息");
        logger.trace("这是 trace 日志");
        logger.debug("这是 debug 日志");
        logger.info("这是 info 日志");
        logger.warn("这是 warn 日志");
        logger.error("这是 error 日志");

        logService.log();
        return returnMap;
    }



}
