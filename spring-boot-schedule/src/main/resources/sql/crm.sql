-- 直接看示例语句，看那些sql语法真是累，都没有示例
-- crm是库名， utf8mb4是字符集， utf8mb4_bin是排序
-- crm Customer Relationship Management 客户关系管理
create database crm character set utf8mb4 collate utf8mb4_bin;



-- 具体mysql/MariaDB数据库规范见 TODO
create table `crmScheduleTask` (
    `scheduleTaskId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
    `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
    `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
    `scheduleTaskName` char(64) NOT NULL COMMENT '定时任务名称，语义化描述',
    `cron` char(64) NOT NULL COMMENT 'cron表达式',
    `className` char(255) NOT NULL COMMENT '任务类的全路径名，这样程序才能通过反射实例化任务类/不写成反射了，换一种简单写法，具体见代码',
    `methodName` char(255) NOT NULL COMMENT '任务类的方法，这样程序才能通过反射调用方法/不写成反射了，换一种简单写法，具体见代码',
    `beanName` char(255) NOT NULL COMMENT '实例化交由spring容器，那这里就通过beanName获取定时任务的实例',
    `isEnable` tinyint NOT NULL COMMENT '1表示开启任务，0表示停止任务',
    PRIMARY KEY (`scheduleTaskId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='用户信息表，演示登录验证的数据，根据你实例化定时任务的方法去使用字段';
insert into crmScheduleTask (scheduleTaskId, createTime, modifiedTime, scheduleTaskName, cron, className, methodName, beanName, isEnable) values
(1, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 'BirthdayTask', '0/10 * * * * *', 'com.demo.schedule.schedule.BirthdayTask', 'run', 'birthdayTask', 1),
(2, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 'CouponTask', '0/30 * * * * *', 'com.demo.schedule.schedule.CouponTask', 'run', 'couponTask', 0);

















