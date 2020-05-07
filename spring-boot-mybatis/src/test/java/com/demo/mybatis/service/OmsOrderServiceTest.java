package com.demo.mybatis.service;


import com.demo.mybatis.MybatisApplication;
import com.demo.mybatis.util.SnowFlakeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = MybatisApplication.class)
@RunWith(value = SpringRunner.class)
public class OmsOrderServiceTest {

    @Autowired
    OmsOrderService omsOrderService;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Test
    public void test1() throws Exception {
        Map<String, Object> omsOrder1 = new HashMap<>();
        omsOrder1.put("orderId", 1L);
        omsOrder1.put("createTime", new Timestamp(System.currentTimeMillis()));
        omsOrder1.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        omsOrder1.put("customerId", 1);
        omsOrder1.put("customerName", "cwm");

        Map<String, Object> omsOrder2 = new HashMap<>();
        omsOrder2.put("orderId", 2L);
        omsOrder2.put("createTime", new Timestamp(System.currentTimeMillis()));
        omsOrder2.put("modifiedTime", new Timestamp(System.currentTimeMillis()));
        omsOrder2.put("customerId", 2);
        omsOrder2.put("customerName", "hwy");

        omsOrderService.insertTwoRecordV1(omsOrder1, omsOrder2);
    }


}
