package com.demo.schedule.schedule;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 优惠券快到期通知定时任务
 *
 * 思路
 * 没有用反射实例化的写法
 * 直接 @Component 让spring实例化，任务需要哪些依赖，自己从spring IOC中注入
 * 实现 Runnable接口 因为spring schedule 编程式创建定时任务ScheduledTask
 * */
@Component
public class CouponTask implements Runnable {

    @Override
    public void run() {
        System.out.println(this.getClass().getName() + "优惠券快到期通知定时任务");
    }

}
