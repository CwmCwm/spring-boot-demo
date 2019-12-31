package com.demo.https.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
public class HttpsClientController {

    /**
     * 这里注入id="restTemplate" 的bean，可以打断点验证一下
     * */
    @Autowired
    RestTemplate restTemplate;

    /**
     * 这里注入id="httpsRestTemplate" 的bean
     * */
    @Autowired
    RestTemplate httpsRestTemplate;

    /**
     * 这里访问 https://www.baidu.com 也是使用https请求，但是百度这些公司的域名是CA机构授信的，CA机构相当于第三方认证，
     * 所以这里restTemplate 无需配置ssl也可以访问百度的https服务，对比下面理解CA机构
     * */
    @RequestMapping(value = "/httpsClientController/test1")
    @ResponseBody
    public Object test1() {
        String responseObject = restTemplate.getForObject("https://www.baidu.com", String.class);
        return responseObject;
    }

    /**
     * 这里访问 https://127.0.0.1:8443/httpsServerController/test1 使用https请求，但是这个https服务使用的证书是自己keytool生成的，不是CA机构认证颁发的
     * 所以要在restTemplate 需要配置ssl也可以访问自己的https服务
     * */
    @RequestMapping(value = "/httpsClientController/test2")
    @ResponseBody
    public Object test2() {
        Object responseObject = httpsRestTemplate.getForObject("https://127.0.0.1:8443/httpsServerController/test1", String.class);
        return responseObject;
    }

}
