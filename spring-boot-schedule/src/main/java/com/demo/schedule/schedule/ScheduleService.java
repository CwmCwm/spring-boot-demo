package com.demo.schedule.schedule;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;


/**
 * CommandLineRunner接口，spring提供的扩展点，在spring容器启动完成后，会调用所有实现CommandLineRunner接口的实例的方法
 *
 * 实现数据库 crmScheduleTask表配置来启动定时任务
 * 提供方法来停止 正在运行的定时任务
 * 提供方法来启动 以停止的定时任务
 *
 * 你看到懂org.springframework.scheduling.config.ScheduledTaskRegistrar 的属性和方法就知道自己该这么写这个类
 * 这个ScheduledTaskRegistrar 提供的方法写起来挺麻烦的，所以自己写个ScheduleService 来管理数据表配置的定时任务
 * */
@Component
public class ScheduleService implements CommandLineRunner, ApplicationContextAware {

    @Resource(name = "crmNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate crmNamedParameterJdbcTemplate;

    //注入定时任务线程池，下面会把定时任务扔进线程池中
    @Autowired
    private ScheduledExecutorService scheduledExecutorService;

    //模仿 ScheduledTaskRegistrar 从scheduledExecutorService构造出TaskScheduler
    //这样 ScheduleService 和 ScheduledTaskRegistrar 公用同一个ScheduledExecutorService实例
    //当然你也可以在这里自己实例化ScheduledExecutorService，ScheduleService 和 ScheduledTaskRegistrar各用各的
    private TaskScheduler taskScheduler;

    //key为beanName , value为ScheduledFuture  ,  方便查找出 ScheduledFuture
    private ConcurrentHashMap<String, ScheduledFuture> scheduledFutureMap = new ConcurrentHashMap<>(16);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 实现CommandLineRunner接口
     * 查询crmScheduleTask表配置来启动定时任务
     * */
    @Override
    public void run(String... args) throws Exception {
        //模仿 ScheduledTaskRegistrar 从scheduledExecutorService构造出TaskScheduler
        taskScheduler = new ConcurrentTaskScheduler(scheduledExecutorService);

        String sqlString = "select * from crmScheduleTask ";
        List<Map<String, Object>> crmScheduleTasks = crmNamedParameterJdbcTemplate.queryForList(sqlString, new HashMap<>());
        for (Map<String, Object> crmScheduleTask : crmScheduleTasks) {
            Integer isEnable = (Integer) crmScheduleTask.get("isEnable");
            if (isEnable == 1) {
                /**
                 * 下面就是构造对应的类，放入taskScheduler中执行，放入 scheduledFutureMap
                 * ScheduledTask的构造器不是public，算了不封装成ScheduledTask
                 * */
                String beanName = crmScheduleTask.get("beanName").toString();
                Object task = applicationContext.getBean(beanName);
                CronTask cronTask = new CronTask((Runnable)task, crmScheduleTask.get("cron").toString());
                ScheduledFuture scheduledFuture = taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
                scheduledFutureMap.put(beanName, scheduledFuture);
            }
        }
    }

    /**
     * 提供启动任务的方法
     * */
    public void startTask(Map<String, Object> crmScheduleTask) throws Exception {
        String beanName = crmScheduleTask.get("beanName").toString();
        if (scheduledFutureMap.containsKey(beanName)) {
            throw new Exception("该任务已经启动，不要重复启动");
        }
        Object task = applicationContext.getBean(beanName);
        CronTask cronTask = new CronTask((Runnable)task, crmScheduleTask.get("cron").toString());
        ScheduledFuture scheduledFuture = taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        scheduledFutureMap.put(beanName, scheduledFuture);
    }

    /**
     * 提供停止任务的方法
     * */
    public void cancelTask(Map<String, Object> crmScheduleTask) throws Exception {
        String beanName = crmScheduleTask.get("beanName").toString();
        if (!scheduledFutureMap.containsKey(beanName)) {
            throw new Exception("该任务没有启动，怎么停止");
        }
        scheduledFutureMap.get(beanName).cancel(true);
        scheduledFutureMap.remove(beanName);
    }

}
