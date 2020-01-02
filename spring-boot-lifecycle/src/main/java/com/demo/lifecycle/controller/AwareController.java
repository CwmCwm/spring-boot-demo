package com.demo.lifecycle.controller;


import com.demo.lifecycle.bean.Tiger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * 使用了spring Aware 你的bean将会和spring框架耦合，
 * spring aware 的目的是为了让bean获取spring容器的服务
 * 注意使用场景
 *
 * 下面各种Aware的子接口的作用，看看Aware子接口还有哪些
 * ApplicationContextAware： 当前的applicationContext， 这也可以调用容器的服务
 * ApplicationEventPublisherAware：应用事件发布器，可以发布事件，
 * BeanNameAware ：可以获取容器中bean的名称
 * BeanFactoryAware:获取当前bean factory这也可以调用容器的服务
 * EmbeddedValueResolverAware:
 * EnvironmentAware: 获取环境变量
 * MessageSourceAware：获得message source，这也可以获得文本信息
 * ResourceLoaderAware： 获得资源加载器，可以获得外部资源文件的内容；
 * */
@Controller
public class AwareController implements ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "/awareController/test1")
    @ResponseBody
    public Object test1() {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errorCode", 0);
        returnMap.put("errorMessage", "success");
        Map<String, Object> tigerMap = new HashMap<>();
        Tiger tiger = applicationContext.getBean(Tiger.class);
        tigerMap.put("age", tiger.getAge());
        returnMap.put("tiger", tigerMap);
        return returnMap;
    }


}
