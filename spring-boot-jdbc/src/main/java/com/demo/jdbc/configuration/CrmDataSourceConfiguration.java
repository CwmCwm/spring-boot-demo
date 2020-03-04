package com.demo.jdbc.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CrmDataSourceConfiguration {

    /**
     * Bean 命名，语义清晰
     * @ConfigurationProperties 指定配置项
     * */
    @Bean(name = "crmDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.crm")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 实例化 JdbcTemplate
     * 比起 NamedParameterJdbcTemplate ，还是 NamedParameterJdbcTemplate 语义化更好，推荐使用 NamedParameterJdbcTemplate
     * */
    @Bean(name ="crmJdbcTemplate")
    @Primary
    public JdbcTemplate jdbcTemplate(@Qualifier("crmDataSource") DataSource dataSource) throws Exception {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 实例化 NamedParameterJdbcTemplate
     * NamedParameterJdbcTemplate 比 JdbcTemplate 好用
     * */
    @Bean(name ="crmNamedParameterJdbcTemplate")
    @Primary
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("crmDataSource") DataSource dataSource) throws Exception {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 数据库事务
     * */
    @Bean(name = "crmTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("crmDataSource") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
