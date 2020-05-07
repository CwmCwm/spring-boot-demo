package com.demo.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;


/**
 springAOP  spring事务

 @EnableTransactionManagement 引入下面两个类
 org.springframework.context.annotation.AutoProxyRegistrar
 org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration

 * */

//@EnableTransactionManagement
@SpringBootApplication
public class MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}

}
