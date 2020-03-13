package com.demo.schedule.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Component 当然要实例化
 * @Scheduled 是基于注解方式
 * 这种方式适用于定时任务写死，你想服务运行时，停止某些定时任务，过段时间，再开启某前面停止的定时任务，怎么办=》凉拌，这种方式没办法
 *
 *
 * 其实可以将定时任务的配置放到数据库，程序定时读取数据库中的定时任务配置，然后判断启动/停止定时任务
 * */
@Component
public class Task1 {

    /**
     * @Scheduled 打上注解就表示这个方法会按照定时配置进行定时任务
     *   cron cron表达式，懂Linux定时任务就懂，不懂百度
     *   其他属性见源码，有注释说明
     *
     * 注意：当方法的执行时间超过任务调度频率时，调度器会在下个周期执行。
     * 如：假设cron()方法在第0秒开始执行，方法执行了12秒，那么下一次执行work()方法的时间是第20秒。
     * 这里是每隔10s输出，关于调度器如何调度，自己实验
     * */
//    @Scheduled(cron = "0/10 * * * * *")
//    public void cron() throws InterruptedException {
//        System.out.println("com.demo.schedule.schedule.Task1.cron()  start " + System.currentTimeMillis());
//        Thread.sleep(12*1000);
//        System.out.println("com.demo.schedule.schedule.Task1.cron()  end   " + System.currentTimeMillis());
//    }


    /**
     * 下一次的任务执行时间，是从方法最后一次任务执行结束时间开始计算。并以此规则开始周期性的执行任务。
     * 假设work()方法在第0秒开始执行，方法执行了12秒，那么下一次执行work()方法的时间是第22秒。
     * */
//    @Scheduled(fixedDelay = 1000*10)
//    public void fixedDelay() throws InterruptedException {
//        System.out.println("com.demo.schedule.schedule.Task1.fixedDelay()  start " + System.currentTimeMillis());
//        Thread.sleep(12*1000);
//        System.out.println("com.demo.schedule.schedule.Task1.fixedDelay()  end   " + System.currentTimeMillis());
//    }

    /**
     * 按照指定频率执行任务，并以此规则开始周期性的执行调度
     * 注意：当方法的执行时间超过任务调度频率时，调度器会在当前方法执行完成后立即执行下次任务。
     * 例如：假设work()方法在第0秒开始执行，方法执行了12秒，那么下一次执行work()方法的时间是第12秒。
     * */
//    @Scheduled(fixedRate = 1000*10)
//    public void fixedRate() throws InterruptedException {
//        System.out.println("com.demo.schedule.schedule.Task1.fixedRate()  start " + System.currentTimeMillis());
//        Thread.sleep(12*1000);
//        System.out.println("com.demo.schedule.schedule.Task1.fixedRate()  end   " + System.currentTimeMillis());
//    }

}
