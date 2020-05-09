package com.demo.mybatis.configuration;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
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
 * 我不写动态数据库源的，什么还要写路由实现/切面实现，我就这里指定数据源的名称，service就按名称注入
 *
 * crm 数据库配置
 *
 * @MapperScan
 * basePackages：dao所在包
 * sqlSessionTemplateRef：表示dao使用的SqlSessionTemplate
 * */
@Configuration
// @MapperScan(basePackages = "com.demo.mybatis.dao.mapper.crm", sqlSessionTemplateRef  = "crmSqlSessionTemplate")
public class CrmDataSourceConfiguration {

    /**
     这里 configLocation 和 mapperLocations 是通过@Value 注入application.properties中的值
     下面
     @Bean(name = "crmSqlSessionFactory")
     @Primary
     public SqlSessionFactory sqlSessionFactory(@Qualifier("crmDataSource") DataSource dataSource) throws Exception
     中使用了 configLocation 和 mapperLocations
     也就是说这里是有循序的，spring先注入 configLocation 和 mapperLocations ，后解析实例化name=crmSqlSessionFactory的SqlSessionFactory实例
     * */
    @Value("${mybatis.crm.config-location}")
    private String configLocation;

    @Value("${mybatis.crm.mapper-locations}")
    private String mapperLocations;

    /**
     * Bean 命名，语义清晰
     * @ConfigurationProperties 指定配置项
     * @Primary 自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
     * */
    @Bean(name = "crmDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.crm")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 实例化 SqlSessionFactory ，这里通过SqlSessionFactory就能进行全局配置，下面各种set方法
     * Bean 命名，语义清晰
     * FactoryBean 就是spring的 org.springframework.beans.factory.FactoryBean 百度一下
     * */
    @Bean(name = "crmSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("crmDataSource") DataSource dataSource) throws Exception {
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
    @Bean(name = "crmTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("crmDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 用于注入DAO
     * ok，配置完事了，Repository 就可以引入crmSqlSessionTemplate
     * */
    @Bean(name = "crmSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("crmSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
