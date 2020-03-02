package com.demo.mybatis.configuration;


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
 * oms 数据库配置
 * */
@Configuration
public class OmsDataSourceConfiguration {

    @Value("${mybatis.oms.config-location}")
    private String configLocation;

    @Value("${mybatis.oms.mapper-locations}")
    private String mapperLocations;

    /**
     * Bean 命名，语义清晰
     * @ConfigurationProperties 指定配置项
     * */
    @Bean(name = "omsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oms")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 实例化 SqlSessionFactory ，这里通过SqlSessionFactory就能进行全局配置，下面各种set方法
     * Bean 命名，语义清晰
     * FactoryBean 就是spring的 org.springframework.beans.factory.FactoryBean 百度一下
     * */
    @Bean(name = "omsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("omsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * Bean 命名，语义清晰
     * 创建事务，因为多数据库，事务操作时要指定事务管理 DataSourceTransactionManager，不指定spring就用默认的，
     * 这里@Primary 就是默认的
     * */
    @Bean(name = "omsTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("omsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 用于注入DAO
     * ok，配置完事了，Repository 就可以引入omsSqlSessionTemplate
     * */
    @Bean(name = "omsSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("omsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
