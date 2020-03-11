-- 直接看示例语句，看那些sql语法真是累，都没有示例
-- crm是库名， utf8mb4是字符集， utf8mb4_bin是排序
-- crm Customer Relationship Management 客户关系管理
create database crm character set utf8mb4 collate utf8mb4_bin;



-- 具体mysql/MariaDB数据库规范见 TODO
create table `crmCustomer` (
    `customerId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
    `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
    `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
    `customerName` char(64) NOT NULL COMMENT '用户名称',
    `username` char(64) NOT NULL COMMENT '账号，命名跟spring security的字段同名',
    `password` char(64) NOT NULL COMMENT '密码，命名跟spring security的字段同名，密码无加密',
    PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='用户信息表，演示登录验证的数据';
create unique index `ukUsername` on crmCustomer(`username`);
insert into crmCustomer (customerId, createTime, modifiedTime, customerName, username, password) values
(1, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 'cwm', 'cwm', 'cwm'),
(2, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 'hwy', 'hwy', 'hwy');




create table `crmRole` (
   `roleId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
   `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
   `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
   `roleName` char(64) NOT NULL COMMENT '角色名称',
   `roleDescribe` char(255) NOT NULL COMMENT '角色描述，起个名称我要知道更具体的语义',
   PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='角色表，演示角色';
insert into crmRole (roleId, createTime, modifiedTime, roleName, roleDescribe) values
(11, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 'admin', '管理员，我最大，你懂得'),
(12, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 'customerService', '客服人员，Order订单接口，Customer顾客信息接口');




create table `crmCustomerRole` (
    `customerRoleId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
    `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
    `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
    `customerId` bigint NOT NULL COMMENT '关联crmCustomer.customerId',
    `roleId` bigint NOT NULL COMMENT '关联crmRole.roleId',
    PRIMARY KEY (`customerRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='crmCustomer与crmRole的中间关联表，一个用户可以有多个角色';
insert into crmCustomerRole (customerRoleId, createTime, modifiedTime, customerId, roleId) values
(101, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 1, 11),
(102, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 2, 12);




create table `crmApi` (
    `apiId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
    `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
    `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
    `apiUrl` char(255) NOT NULL COMMENT 'api接口，就是Controller上的接口',
    `apiDescribe` char(255) NOT NULL COMMENT 'api描述，语义，以接口文档为准',
    PRIMARY KEY (`apiId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='api接口表，既然要接口权限，那就用定义数据结构';
insert into crmApi (apiId, createTime, modifiedTime, apiUrl, apiDescribe) values
(201, '2020-01-01 12:00:00', '2020-01-01 12:00:00', '/order/get', '见接口文档'),
(202, '2020-01-01 12:00:00', '2020-01-01 12:00:00', '/customer/get', '见接口文档'),
(203, '2020-01-01 12:00:00', '2020-01-01 12:00:00', '/admin/get', '见接口文档');




create table `crmApiRole` (
    `apiRoleId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
    `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
    `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
    `apiId` bigint NOT NULL COMMENT '关联crmApi.apiId',
    `roleId` bigint NOT NULL COMMENT '关联crmRole.roleId',
    PRIMARY KEY (`apiRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='crmApi和crmRole的中间关联表';
insert into crmApiRole (apiRoleId, createTime, modifiedTime, apiId, roleId) values
(301, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 201, 11),
(302, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 202, 11),
(303, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 203, 11),
(304, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 201, 12),
(305, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 202, 12);



create table `crmMenu` (
    `menuId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
    `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
    `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
    `menuName` char(255) NOT NULL COMMENT '菜单名称',
    `menuDescribe` char(255) NOT NULL COMMENT '语义描述',
    `parentMenuId` bigint NOT NULL COMMENT '关联crmMenu.menuId，用于构成菜单树形结构，一级菜单的parentMenuId为0',
    PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='菜单表';
insert into crmMenu (menuId, createTime, modifiedTime, menuName, menuDescribe, parentMenuId) values
(401, '2020-01-01 12:00:00', '2020-01-01 12:00:00', '订单Order', '订单Order', 0),
(402, '2020-01-01 12:00:00', '2020-01-01 12:00:00', '查询订单', '查询订单', 401),
(403, '2020-01-01 12:00:00', '2020-01-01 12:00:00', '用户Customer', '用户Customer', 0),
(404, '2020-01-01 12:00:00', '2020-01-01 12:00:00', '查询用户', '查询用户', 403),
(405, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 'Admin', 'Admin', 0);




create table `crmMenuRole` (
    `menuRoleId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
    `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
    `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
    `menuId` bigint NOT NULL COMMENT '关联crmMenu.menuId',
    `roleId` bigint NOT NULL COMMENT '关联crmRole.roleId',
    PRIMARY KEY (`menuRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='crmMenu和crmRole的中间关联表';
insert into crmMenuRole (menuRoleId, createTime, modifiedTime, menuId, roleId) values
(501, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 401, 11),
(502, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 402, 11),
(503, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 403, 11),
(504, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 404, 11),
(505, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 405, 11),
(506, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 401, 12),
(507, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 402, 12),
(508, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 403, 12),
(509, '2020-01-01 12:00:00', '2020-01-01 12:00:00', 404, 12);














