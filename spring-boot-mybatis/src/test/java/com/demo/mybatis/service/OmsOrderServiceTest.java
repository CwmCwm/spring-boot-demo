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

    //这里引入Service层，主要是演示数据库事务的写法
    @Resource(name = "omsNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate omsNamedParameterJdbcTemplate;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;


}
