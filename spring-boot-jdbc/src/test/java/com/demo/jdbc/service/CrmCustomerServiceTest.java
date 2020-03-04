package com.demo.jdbc.service;


import com.demo.jdbc.JDBCApplication;
import com.demo.jdbc.repository.CrmCustomerRepository;
import com.demo.jdbc.util.SnowFlakeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = JDBCApplication.class)
@RunWith(value = SpringRunner.class)
public class CrmCustomerServiceTest {

    //这里引入Service层，主要是演示数据库事务的写法
    @Resource(name = "crmNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate;

    @Autowired
    private CrmCustomerRepository crmCustomerRepository;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Test
    public void insert() {
        Map<String, Object> crmCustomer = new HashMap<>();
        Long customerId = snowFlakeUtil.nextId();
        crmCustomer.put("customerId", customerId);
        crmCustomer.put("createTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("customerName", customerId.toString());
        int result = crmCustomerRepository.insert(crmCustomer);
        System.out.println(result);
    }

    @Test
    public void update() {
        Map<String, Object> crmCustomer = new HashMap<>();
        crmCustomer.put("customerId", 1234683155533926400L);
        crmCustomer.put("createTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("customerName", "111");
        int result = crmCustomerRepository.update(crmCustomer);
        System.out.println(result);
    }

    @Test
    public void delete() {
        Map<String, Object> crmCustomer = new HashMap<>();
        crmCustomer.put("customerId", 1234683155533926400L);
        int result = crmCustomerRepository.delete(crmCustomer);
        System.out.println(result);
    }

    @Test
    public void selectAll() {
        Map<String, Object> crmCustomer = new HashMap<>();
        List<Map<String, Object>> crmCustomers = crmCustomerRepository.selectAll(crmCustomer);
        System.out.println(crmCustomers);
    }


}
