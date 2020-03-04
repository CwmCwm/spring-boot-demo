package com.demo.jdbc.configuration;


import com.demo.jdbc.util.SnowFlakeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 配置实例化 SnowFlakeUtil ，因为这个类要初始化一些参数，所以在这里初始化
 * SnowFlake的使用场景请自行思考和百度
 * */
@Configuration
public class SnowFlakeUtilConfiguration {

    @Bean(name = "snowFlakeUtil")
    public SnowFlakeUtil snowFlakeUtil() {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(1, 1, 1);
        return snowFlakeUtil;
    }

}
