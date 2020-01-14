package com.demo.actuator;


import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * HealthIndicator org.springframework.boot.actuate.health.HealthIndicator 用于健康业务逻辑的判断
 * HealthIndicator接口，看一下实现，如DataSourceHealthIndicator 就是判断数据库是否健康；DiskSpaceHealthIndicator 就是磁盘是否健康
 *
 * 访问 http://127.0.0.1:8080/actuator/health 试试
 * */
@Component
public class DiyHealthIndicator implements HealthIndicator {

    /**
     * 每次访问 /health 节点，都会实时执行一次进行健康判断
     * */
    @Override
    public Health health() {
        // 自定义判断代码，如kafka连接
        Health.Builder builder = new Health.Builder();
        Map<String, Object> map = new HashMap<>();
        map.put("diy1", "123");
        map.put("diy2", "abc");
        int random = new Random().nextInt(10);
        System.out.println(random);
        if((random % 2) == 0) {
            builder.up();
        } else {
            builder.down();
        }
        builder.withDetails(map);
        return builder.build();
    }

}
