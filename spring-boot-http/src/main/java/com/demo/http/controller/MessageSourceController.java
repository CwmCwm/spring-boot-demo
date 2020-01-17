package com.demo.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * 国际化文件支持，应用场景：用户前端选择语言，cookie-session保存语言选择，然后在Controller层做个映射（相当于翻译）
 * 要支持国际化，就需要在resources/ 目录下配置多个messages.properties
 * 如本项目 messages.properties表示默认的，messages_en_US.properties表示英文，messages_zh_CN.properties表示中文
 * 百度国际化i18n的命名规范，就知道messages.properties 如何命名
 * */
@Controller
public class MessageSourceController {

    /**
     * spring-boot默认实现的MessageSource bean，通过该bean就可以拿到不同的messages.properties中的数据
     * 具体使用看 MessageSource接口的注释
     * */
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/messageSourceController/messageSource")
    @ResponseBody
    public Object messageSource() {
        Map<String, Object> responseMap = new HashMap<>(3);
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");

        // 不指定Locale参数，那么就按服务器/操作系统的地区
        responseMap.put("default", messageSource.getMessage("hello", null, "", null));
        // 指定Locale.US地区
        responseMap.put("en_US", messageSource.getMessage("hello", null, "", Locale.US));
        // 指定Locale.SIMPLIFIED_CHINESE地区
        responseMap.put("zh_CN", messageSource.getMessage("hello", null, "", Locale.SIMPLIFIED_CHINESE));
        // 指定Locale.FRENCH地区，法国
        responseMap.put("fr", messageSource.getMessage("hello", null, "", Locale.FRENCH));

        return responseMap;
    }



}
