package com.demo.http.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;


/**
 redis的连接配置
 这里啥都没写，就用spring-boot 默认的
 你要自定义自己的 RedisConnectionFactory（这个是redis连接工厂没啥好自定义） 和 RedisTemplate 就自己写咯
 一般就自定义 RedisTemplate，因为要定义全局的 redis数据类型和java数据类型的序列化和反序列
 * */
@Configuration
public class RedisConfiguration {

    /**
     application.properties中配置了redis的连接参数
     spring-boot2 默认的redis连接工具是lettuce，不再是jedis
     你可以在
     * */
    @Autowired
    RedisConnectionFactory redisConnectionFactory;


}
