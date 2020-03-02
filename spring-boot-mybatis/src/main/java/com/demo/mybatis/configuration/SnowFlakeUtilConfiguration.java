package com.demo.mybatis.configuration;


import com.demo.mybatis.util.SnowFlakeUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * 配置实例化 SnowFlakeUtil ，因为这个类要初始化一些参数，所以在这里初始化
 * SnowFlake的使用场景请自行思考和百度
 * */
@Configuration
public class SnowFlakeUtilConfiguration {

    @Bean(name = "snowFlakeUtil")
    public SnowFlakeUtil snowFlakeUtil() {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(1, 1, 1);
        return snowFlakeUtil;
    }

}
