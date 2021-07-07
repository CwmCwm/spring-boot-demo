package com.demo.http.configuration;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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

    /**
     使用JDK自带的ConcurrentMap作为缓存，没有依赖，问题是没有缓存策略，如LRU，FIFO，LFU
     因为这个就只是简单的存和取
     * */
    @Primary
    @Bean(name = "concurrentMapCacheManager")
    public ConcurrentMapCacheManager concurrentMapCacheManager(CacheProperties cacheProperties) {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        return concurrentMapCacheManager;
    }

    /**
     使用redis作缓存，缓存策略，见redis啊，redis怎么设计就怎么用，这里无非是在java代码中进行配置
     redis缓存无非是给缓存设置过期时间
     **/
    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager(CacheProperties cacheProperties,
                                               ObjectProvider<RedisCacheConfiguration> redisCacheConfiguration,
                                               ObjectProvider<RedisCacheManagerBuilderCustomizer> redisCacheManagerBuilderCustomizers,
                                               RedisConnectionFactory redisConnectionFactory, ResourceLoader resourceLoader) {
        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory).build();
        return redisCacheManager;
    }

    /**
     使用EhCacheCacheManager，提供LRU，FIFO，LFU
     需要jar包依赖
     * */
//    @Bean(name = "ehCacheManager")
//    public EhCacheCacheManager ehCacheManager() {
//        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
//        return ehCacheManager;
//    }

    /*
     据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     加载配置文件ehcache.xml
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared (true);
        return cacheManagerFactoryBean;
    }

    /*
     按照上面加载配置文件ehcache.xml 来创建
     */
    @Bean(name = "ehCacheManager")
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        System.setProperty(net.sf.ehcache.CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY, "true");
        return new EhCacheCacheManager (bean.getObject ());
    }


}
