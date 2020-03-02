package com.demo.mybatis.service;


import com.demo.mybatis.MybatisApplication;
import com.demo.mybatis.util.SnowFlakeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = MybatisApplication.class)
@RunWith(value = SpringRunner.class)
public class OmsOrderServiceTest {

    @Autowired
    private OmsOrderService omsOrderService;
    @Resource(name = "omsSqlSessionTemplate")
    private SqlSessionTemplate omsSqlSessionTemplate;
    @Autowired
    private SnowFlakeUtil snowFlakeUtil;


    //通过主键重复演示抛异常来回滚事务，自己构造数据
    @Test
    public void insertTwoRecordV1() {
        Map<String, Object> omsOrder1 = new HashMap<>();
        omsOrder1.put("orderId", 5L);
        omsOrder1.put("createTime", new Timestamp(System.currentTimeMillis()));
        omsOrder1.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        omsOrder1.put("customerId", 1L);
        omsOrder1.put("customerName", "gg");

        Map<String, Object> omsOrder2 = new HashMap<>();
        omsOrder2.put("orderId", 5L);
        omsOrder2.put("createTime", new Timestamp(System.currentTimeMillis()));
        omsOrder2.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        omsOrder2.put("customerId", 2L);
        omsOrder2.put("customerName", "hh");
        int result = omsOrderService.insertTwoRecordV1(omsOrder1, omsOrder2);
        System.out.println(result);
    }

    //通过主键重复演示抛异常来回滚事务，自己构造数据
    @Test
    public void insertTwoRecordV2() {
        Map<String, Object> omsOrder1 = new HashMap<>();
        omsOrder1.put("orderId", 12L);
        omsOrder1.put("createTime", new Timestamp(System.currentTimeMillis()));
        omsOrder1.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        omsOrder1.put("customerId", 1L);
        omsOrder1.put("customerName", "gg");

        Map<String, Object> omsOrder2 = new HashMap<>();
        omsOrder2.put("orderId", 12L);
        omsOrder2.put("createTime", new Timestamp(System.currentTimeMillis()));
        omsOrder2.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        omsOrder2.put("customerId", 2L);
        omsOrder2.put("customerName", "hh");
        int result = omsOrderService.insertTwoRecordV2(omsOrder1, omsOrder2);
        System.out.println(result);
    }

    //通过主键重复演示抛异常来回滚事务，自己构造数据
    @Test
    public void insertTwoRecordV3() {
        Map<String, Object> crmCustomer = new HashMap<>();
        crmCustomer.put("customerId", 13L);
        crmCustomer.put("createTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("customerName", "gg");

        Map<String, Object> omsOrder = new HashMap<>();
        omsOrder.put("orderId", 13L);
        omsOrder.put("createTime", new Timestamp(System.currentTimeMillis()));
        omsOrder.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        omsOrder.put("customerId", 2L);
        omsOrder.put("customerName", "hh");
        int result = omsOrderService.insertTwoRecordV3(crmCustomer, omsOrder);
        System.out.println(result);
    }

    //通过主键重复演示抛异常来回滚事务，自己构造数据
    @Test
    public void insertTwoRecordV4() {
        Map<String, Object> crmCustomer = new HashMap<>();
        crmCustomer.put("customerId", 14L);
        crmCustomer.put("createTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        crmCustomer.put("customerName", "gg");

        Map<String, Object> omsOrder = new HashMap<>();
        omsOrder.put("orderId", 14L);
        omsOrder.put("createTime", new Timestamp(System.currentTimeMillis()));
        omsOrder.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        omsOrder.put("customerId", 2L);
        omsOrder.put("customerName", "hh");
        int result = omsOrderService.insertTwoRecordV4(crmCustomer, omsOrder);
        System.out.println(result);
    }

}
