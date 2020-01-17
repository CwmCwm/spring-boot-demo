package com.demo.log.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 日志测试使用
 * */
@Service
public class LogService {

    /**
     * 面向接口编程的思想
     * spring-boot默认使用slf4j日志门面，注意引入的类 org.slf4j.Logger
     * */
    private final Logger logger = LoggerFactory.getLogger(LogService.class);

    /**
     * 日志测试使用
     * */
    public void log() {
        logger.trace("这是 trace 日志");
        logger.debug("这是 debug 日志");
        logger.info("这是 info 日志");
        logger.warn("这是 warn 日志");
        logger.error("这是 error 日志");
    }

}
