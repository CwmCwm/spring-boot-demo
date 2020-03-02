-- 直接看示例语句，看那些sql语法真是累，都没有示例
-- oms是库名， utf8mb4是字符集， utf8mb4_bin是排序
-- oms Order Management System 订单管理系统
create database oms character set utf8mb4 collate utf8mb4_bin;



-- 具体mysql/MariaDB数据库规范见 TODO
create table `omsOrder` (
  `orderId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
  `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
  `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
  `customerId` bigint unsigned NOT NULL COMMENT '关联crmCustomer.customerId',
  `customerName` char(64) NOT NULL COMMENT '顾客名称，关联crmCustomer.customerName，这里冗余设计，因为一般不会修改crmCustomer.customerName',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='订单表';




