package com.demo.jdbc.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;


/**
 * 这里就是 DAO/Repository层
 * 这一层就是写sql语句
 * 自行拼接sql语句，然后调用 NamedParameterJdbcTemplate 执行
 * 拼接sql语句没有mybatis 方便 和 舒服，其他从使用学习角度上说相同
 *
 * 常用的 NamedParameterJdbcTemplate api，我就用三个
 * queryForList
 * queryForMap
 * update
 * 够用了
 *
 * 关于数据库事务，和spring-boot-mybatis 项目一模一样的写法，在service层写，所以这里就没写
 * */
@Repository
public class CrmCustomerRepository {

    Connection

    @Resource(name = "crmNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate;

    /**
     * 插入一条记录
     * */
    public int insert (Map<String, Object> crmCustomer) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append(" insert into crmCustomer (customerId, createTime, modifiedTime, customerName) ");
        sqlStringBuilder.append(" values ");
        //如果JdbcTemplate 拼接的sql语句就是 ? 问号， 使用NamedParameterJdbcTemplate 拼接sql语句就是 :字段名 ，所以说语义化更好
        sqlStringBuilder.append(" (:customerId, :createTime, :modifiedTime, :customerName) ");
        String sqlString = sqlStringBuilder.toString();
        return crmNamedParameterJdbcTemplate.update(sqlString, crmCustomer);
    }

    /**
     * 根据主键更新一条记录
     * */
    public int update (Map<String, Object> crmCustomer) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append(" update crmCustomer set ");
        if (crmCustomer.containsKey("modifiedTime")) {
            sqlStringBuilder.append(" modifiedTime=:modifiedTime,");
        }
        if (crmCustomer.containsKey("modifiedTime")) {
            sqlStringBuilder.append(" customerName=:customerName,");
        }
        sqlStringBuilder.deleteCharAt(sqlStringBuilder.length() - 1);
        sqlStringBuilder.append(" where customerId=:customerId ");
        String sqlString = sqlStringBuilder.toString();
        return crmNamedParameterJdbcTemplate.update(sqlString, crmCustomer);
    }

    /**
     * 根据主键删除一条记录
     * */
    public int delete (Map<String, Object> crmCustomer) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append(" delete from crmCustomer where customerId=:customerId ");
        String sqlString = sqlStringBuilder.toString();
        return crmNamedParameterJdbcTemplate.update(sqlString, crmCustomer);
    }

    /**
     * 根据主键删除一条记录
     * */
    public List<Map<String, Object>> selectAll (Map<String, Object> crmCustomer) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append(" select * from crmCustomer where 1=1 ");
        if (crmCustomer.containsKey("customerName")) {
            sqlStringBuilder.append(" and customerName=:customerName");
        }
        String sqlString = sqlStringBuilder.toString();
        return crmNamedParameterJdbcTemplate.queryForList(sqlString, crmCustomer);
    }


}
