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

    @Resource(name = "omsSqlSessionTemplate")
    SqlSessionTemplate omsSqlSessionTemplate;

    @Test
    public void test1() throws Exception {
        for (int i=1; i<1000000; i++) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("t1_id", i);
            Map<String, Object> result = omsSqlSessionTemplate.selectOne("com.demo.mybatis.omsMapper.t1Mapper.selectOneById", paramMap);
            System.out.println(result);
        }
    }


    @Test
    public void test2() throws Exception {
        for (int i=1; i<100; i++) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("t1_id", 1);
            Map<String, Object> result = omsSqlSessionTemplate.selectOne("com.demo.mybatis.omsMapper.t1Mapper.selectOneById", paramMap);
            System.out.println(result);
        }
    }


}
