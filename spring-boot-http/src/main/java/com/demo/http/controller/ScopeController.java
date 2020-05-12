package com.demo.http.controller;

import com.demo.http.component.ScopePrototypeComponent;
import com.demo.http.component.ScopeRequestComponent;
import com.demo.http.component.ScopeSessionComponent;
import com.demo.http.component.ScopeSingletonComponent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * */
@Controller
public class ScopeController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     测试单例模式
     * */
    @RequestMapping("/scope/scopeSingleton")
    @ResponseBody
    public Object scopeSingleton(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();
        //看看对象的 hash是否一样
        System.out.println(applicationContext.getBean(ScopeSingletonComponent.class));
        System.out.println(applicationContext.getBean(ScopeSingletonComponent.class));
        System.out.println(applicationContext.getBean(ScopeSingletonComponent.class));

        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        return responseMap;
    }

    /**
     测试多例模式
     * */
    @RequestMapping("/scope/scopePrototype")
    @ResponseBody
    public Object scopePrototype(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();
        //看看对象的 hash是否一样
        System.out.println(applicationContext.getBean(ScopePrototypeComponent.class));
        System.out.println(applicationContext.getBean(ScopePrototypeComponent.class));
        System.out.println(applicationContext.getBean(ScopePrototypeComponent.class));

        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        return responseMap;
    }

    /**
     测试 SCOPE_REQUEST
     * */
    @RequestMapping("/scope/scopeRequest")
    @ResponseBody
    public Object scopeRequest(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();

        //同一个http请求
        //浏览器再请求一次就是新的http请求
        System.out.println(applicationContext.getBean(ScopeRequestComponent.class));
        System.out.println(applicationContext.getBean(ScopeRequestComponent.class));
        System.out.println(applicationContext.getBean(ScopeRequestComponent.class));

        //debug模式进来，看看，这个要放在下面，因为上面会实例化并缓存入 HttpServletRequest中
        Enumeration<String> attributeNames =  request.getAttributeNames();
        System.out.println(request.getAttribute("scopeRequestComponent"));

        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        return responseMap;
    }

    /**
     测试 SCOPE_SESSION
     * */
    @RequestMapping("/scope/scopeSession")
    @ResponseBody
    public Object scopeSession(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> responseMap = new HashMap<>();

        //同一个浏览器请求多次都是同一个session
        //打开新的浏览器，一个chrome  一个360浏览器  两个浏览器就是不同的session
        System.out.println(applicationContext.getBean(ScopeSessionComponent.class));
        System.out.println(applicationContext.getBean(ScopeSessionComponent.class));
        System.out.println(applicationContext.getBean(ScopeSessionComponent.class));

        //debug模式进来，看看，这个要放在下面，因为上面会实例化并缓存入 HttpSession中
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        System.out.println(request.getSession().getAttribute("scopeSessionComponent"));

        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        return responseMap;
    }

}
