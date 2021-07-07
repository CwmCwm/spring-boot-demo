package com.demo.http.controller;

import com.demo.http.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * */
@Controller
public class CacheController {

    //可以debug看看spring-boot2 默认使用的是 LettuceConnectionFactory
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    RedisTemplate redisTemplate;

    @Resource(name = "redisCacheManager")
    RedisCacheManager redisCacheManager;

    @Resource(name = "concurrentMapCacheManager")
    ConcurrentMapCacheManager concurrentMapCacheManager;

    @Autowired
    UserService userService;

    /**
     *
     * */
    @RequestMapping(value = "/cache/redisCache1")
    @ResponseBody
    public Object redisCache1(@RequestParam(value = "userId") Long userId) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        Map<String, Object> user = userService.selectOneFromRedis1(userId);
        responseMap.put("user", user);
        return responseMap;
    }

    @RequestMapping(value = "/cache/redisCache2")
    @ResponseBody
    public Object redisCache2(@RequestParam(value = "userId") Long userId) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        Map<String, Object> user = userService.selectOneFromRedis2(userId);
        responseMap.put("user", user);
        return responseMap;
    }


    @RequestMapping(value = "/cache/concurrentMapCache1")
    @ResponseBody
    public Object concurrentMapCache1(@RequestParam(value = "userId") Long userId) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        Map<String, Object> user = userService.selectOneFromConcurrentMap1(userId);
        responseMap.put("user", user);
        return responseMap;
    }

    @RequestMapping(value = "/cache/concurrentMapCache2")
    @ResponseBody
    public Object concurrentMapCache2(@RequestParam(value = "userId") Long userId) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        Map<String, Object> user = userService.selectOneFromConcurrentMap2(userId);
        responseMap.put("user", user);
        return responseMap;
    }

    @RequestMapping(value = "/cache/chCacheManager1")
    @ResponseBody
    public Object chCacheManager1(@RequestParam(value = "userId") Long userId) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        Map<String, Object> user = userService.selectOneFromEhCacheManager1(userId);
        responseMap.put("user", user);
        return responseMap;
    }

    @RequestMapping(value = "/cache/chCacheManager2")
    @ResponseBody
    public Object chCacheManager2(@RequestParam(value = "userId") Long userId) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        Map<String, Object> user = userService.selectOneFromEhCacheManager2(userId);
        responseMap.put("user", user);
        return responseMap;
    }


}
