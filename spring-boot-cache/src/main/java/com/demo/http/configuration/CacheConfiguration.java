package com.demo.http.configuration;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.springframework.boot.autoconfigure.cache.*;


/**
 @EnableCaching 开启缓存，也是springAOP的一种使用场景
 按默认的就是使用 org.springframework.cache.interceptor.CacheInterceptor 和  org.springframework.cache.interceptor.CacheAspectSupport

 你可以看 org.springframework.boot.autoconfigure.cache.*  的注册类，模仿学习一下具体的CacheManager如何实例化
 好吧，我看了实例化过程，配置了一堆我不知道的参数和我不知道的方法，我下面就用最简单的方法
  * */
@EnableCaching
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class CacheConfiguration {


    @Primary
    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager(CacheProperties cacheProperties,
                                               ObjectProvider<RedisCacheConfiguration> redisCacheConfiguration,
                                               ObjectProvider<RedisCacheManagerBuilderCustomizer> redisCacheManagerBuilderCustomizers,
                                               RedisConnectionFactory redisConnectionFactory, ResourceLoader resourceLoader) {
        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory).build();
        return redisCacheManager;
    }


    @Bean(name = "concurrentMapCacheManager")
    public ConcurrentMapCacheManager concurrentMapCacheManager(CacheProperties cacheProperties) {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        return concurrentMapCacheManager;
    }

}
