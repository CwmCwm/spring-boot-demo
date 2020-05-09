package com.demo.mybatis.controller;


import com.demo.mybatis.service.CrmCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CrmCustomerController {

    @Autowired
    private CrmCustomerService crmCustomerService;

    @ResponseBody
    @RequestMapping(value = "/crmCustomer/selectAll")
    public Object selectAll () {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        responseMap.put("crmCustomers", crmCustomerService.selectAll(new HashMap<>()));
        return responseMap;
    }

}
