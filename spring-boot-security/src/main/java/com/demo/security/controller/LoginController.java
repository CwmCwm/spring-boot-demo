package com.demo.security.controller;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    //演示就不写Repository层，直接写sql语句了
    @Resource(name = "crmNamedParameterJdbcTemplate")
    NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate;

    /**
     * 这里是http转发，没有传递过来登录失败的异常
     * 因为http转发，可以拿到账号密码，这里再进行一次判断，看看是“账号不存在” 还是 “密码错误” ，细分也就这两种错误
     * 这里没有用spring security 提供的 failureHandler 添加错误处理器的方法，而是使用转发，这样就可以使用全局异常处理器
     * */
    @RequestMapping(value = "/login/fail")
    @ResponseBody
    public Object fail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 401);
        responseMap.put("message", "还有其他未知错误？");

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        //自行验证一次，这里就不写了。就是从数据库查询账号，验证密码
        Map<String, Object> crmCustomer;
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("username", username);
            crmCustomer = crmNamedParameterJdbcTemplate.queryForMap("select * from crmCustomer where username=:username", paramMap);
        } catch (EmptyResultDataAccessException e) {
            responseMap.put("message", "username账号不存在");
            return responseMap;
        }
        if (!password.equals(crmCustomer.get("password").toString())) {
            responseMap.put("message", "password密码错误");
            return responseMap;
        }

        return responseMap;
    }

    /**
     * 这里是http转发，登录成功后转发到这里
     * 前后端分离写法
     * */
    @RequestMapping(value = "/login/success")
    @ResponseBody
    public Object success(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "/login/success");
        return responseMap;
    }

}
