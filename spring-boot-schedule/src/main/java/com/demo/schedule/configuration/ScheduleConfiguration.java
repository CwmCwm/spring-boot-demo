package com.demo.schedule.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 见org.springframework.scheduling.config.ScheduledTaskRegistrar源代码 359行，可以看见默认配置的定时任务线程池是单线程池
 * 关于单线程池和多线程池会有什么影响，想想就知道了
 *
 * 实现SchedulingConfigurer接口去修改定时任务的线程池
 * */
@Configuration
public class ScheduleConfiguration implements SchedulingConfigurer {

    /**
     * 这里实例化定时任务线程池，下面配置用到
     * com.demo.schedule.schedule.ScheduleService 也用到
     * */
    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(10);
    }

    /**
     * 人家提供接口给你修改，你就用接口，如果没有提供接口
     * 那就用通用的技巧
     * 技巧一：找到对应的类，注入对应Bean，它提供set方法就用set方法配置（下面其实就是用set方法）
     * 技巧二：自己实现注册类替换掉默认配置的注册类，这里就是自己实例化 ScheduledTaskRegistrar
     *
     * 就是这样啊，spring就是IOC容器，那就是IOC入手啊
     * */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //配置定时任务的线程池
        taskRegistrar.setScheduler(scheduledExecutorService());
    }


}
