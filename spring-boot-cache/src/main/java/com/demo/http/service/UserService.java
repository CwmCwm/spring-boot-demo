package com.demo.http.service;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 就相当于 user表的Service

 假定user表结构
 create table `user` (
   `userId` bigint unsigned NOT NULL COMMENT '主键',
   `userName` char(32) NOT NULL COMMENT '用户名',
 PRIMARY KEY (`crm1Id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
 COMMENT='user用户表';


 * */
@Service
public class UserService {


    /**
     流程是：
     第一次根据 userId=1 去redis中查找，redis中没记录，去mysql找到记录后，缓存入redis，并返回
     第二次根据 userId=1 去redis中查找，redis中有记录，直接返回

     cacheNames + key  拼接的字符串就是redis中的key值，可以对比selectOneFromRedis1 和 selectOneFromRedis2 调用后，查看redis中的数据得知
     * */
    @Cacheable(cacheNames = "redis1", cacheManager = "redisCacheManager", key = "'user' + #userId")
    public Map<String, Object> selectOneFromRedis1 (Long userId) {
        //这里就当模拟查询数据库，根据主键userId查找返回记录
        System.out.println("根据主键userId在mysql中查找记录userId=" + userId);
        Map<String, Object> user = new HashMap<>();
        user.put("userId", userId);
        user.put("userName", "userName" + userId);
        System.out.println("将userId=" + userId + "放入redisCacheManager缓存");
        return user;
    }

    @Cacheable(cacheNames = "redis2", cacheManager = "redisCacheManager", key = "'user' + #userId")
    public Map<String, Object> selectOneFromRedis2 (Long userId) {
        //这里就当模拟查询数据库，根据主键userId查找返回记录
        System.out.println("根据主键userId在mysql中查找记录userId=" + userId);
        Map<String, Object> user = new HashMap<>();
        user.put("userId", userId);
        user.put("userName", "userName" + userId);
        System.out.println("将userId=" + userId + "放入redisCacheManager缓存");
        return user;
    }


    /**
     selectOneFromConcurrentMap1创建一个 ConcurrentMapCache， selectOneFromConcurrentMap2会创建一个新的 ConcurrentMapCache
     切面类 CacheAspectSupport 会通过注解上的数据 cacheNames = "concurrentMap1" 和 key 去对应的 ConcurrentMapCache实例中操作数据（增查）
     你可在 CacheAspectSupport类->820行  doPut(cache, this.key, result); 上debug看到 cache变量是哪个具体的 Cache（RedisCache，ConcurrentMapCache 等等）
     * */
    @Cacheable(cacheNames = "concurrentMap1", cacheManager = "concurrentMapCacheManager", key = "'user' + #userId")
    public Map<String, Object> selectOneFromConcurrentMap1 (Long userId) {
        //这里就当模拟查询数据库，根据主键userId查找返回记录
        System.out.println("根据主键userId在mysql中查找记录userId=" + userId);
        Map<String, Object> user = new HashMap<>();
        user.put("userId", userId);
        user.put("userName", "userName" + userId);
        System.out.println("将userId=" + userId + "放入concurrentMapCacheManager缓存");
        return user;
    }

    @Cacheable(cacheNames = "concurrentMap2", cacheManager = "concurrentMapCacheManager", key = "'user' + #userId")
    public Map<String, Object> selectOneFromConcurrentMap2 (Long userId) {
        //这里就当模拟查询数据库，根据主键userId查找返回记录
        System.out.println("根据主键userId在mysql中查找记录userId=" + userId);
        Map<String, Object> user = new HashMap<>();
        user.put("userId", userId);
        user.put("userName", "userName" + userId);
        System.out.println("将userId=" + userId + "放入concurrentMapCacheManager缓存");
        return user;
    }


    /**

     * */
    @Cacheable(cacheNames = "ehcache1", cacheManager = "ehCacheManager", key = "'user' + #userId")
    public Map<String, Object> selectOneFromEhCacheManager1 (Long userId) {
        //这里就当模拟查询数据库，根据主键userId查找返回记录
        System.out.println("根据主键userId在mysql中查找记录userId=" + userId);
        Map<String, Object> user = new HashMap<>();
        user.put("userId", userId);
        user.put("userName", "userName" + userId);
        System.out.println("将userId=" + userId + "放入ehCacheManager缓存");
        return user;
    }

    @Cacheable(cacheNames = "ehcache2", cacheManager = "ehCacheManager", key = "'user' + #userId")
    public Map<String, Object> selectOneFromEhCacheManager2 (Long userId) {
        //这里就当模拟查询数据库，根据主键userId查找返回记录
        System.out.println("根据主键userId在mysql中查找记录userId=" + userId);
        Map<String, Object> user = new HashMap<>();
        user.put("userId", userId);
        user.put("userName", "userName" + userId);
        System.out.println("将userId=" + userId + "放入ehCacheManager缓存");
        return user;
    }

}
