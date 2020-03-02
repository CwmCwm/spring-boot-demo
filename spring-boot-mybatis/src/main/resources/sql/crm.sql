-- 直接看示例语句，看那些sql语法真是累，都没有示例
-- crm是库名， utf8mb4是字符集， utf8mb4_bin是排序
-- crm Customer Relationship Management 客户关系管理
create database crm character set utf8mb4 collate utf8mb4_bin;



-- 具体mysql/MariaDB数据库规范见 TODO
create table `crmCustomer` (
  `customerId` bigint unsigned NOT NULL COMMENT '主键，后端程序用SnowFlake算法生成',
  `createTime` datetime NOT NULL COMMENT '创建时间，插入数据的时间',
  `modifiedTime` datetime NOT NULL COMMENT '修改时间，每次修改都要更新该字段',
  `customerName` char(64) NOT NULL COMMENT '顾客名称',
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='顾客信息表';


-- 数据类型演示
create table `crmAllDataType`(
  `dataTypeTinyint` tinyint NOT NULL COMMENT '整形数值，1个字节，tinyint类型对应java.lang.Integer',
  `dataTypeSmallint` smallint NOT NULL COMMENT '整形数值，2个字节，smallint类型对应java.lang.Integer',
  `dataTypeMediumint` mediumint NOT NULL COMMENT '整形数值，3字节，mediumint类型对应java.lang.Integer',
  `dataTypeInt` int NOT NULL COMMENT '整形数值，4个字节，int类型对应java.lang.Integer',
  `dataTypeInteger` integer NOT NULL COMMENT 'int和integer没有区别，是相同的，就是多个名称，推荐用int就行了',
  `dataTypeBigint` bigint NOT NULL COMMENT '整形数值，8个字节，bigint类型对应,java.lang.Long',

  `dataTypeBit` bit NOT NULL COMMENT '二进制位，不是0就是1，bit类型对应java.lang.Boolean，感觉没啥用啊',
  `dataTypeReal` real NOT NULL COMMENT '浮点数值，real类型对应，不推荐使用java.lang.Double，大概跟double和float类似',
  `dataTypeDouble` double NOT NULL COMMENT '浮点数值，非标准数据类型，DB中保存的是近似值，double类型对应java.lang.Double，不推荐使用',
  `dataTypeFloat` float NOT NULL COMMENT '浮点数值，非标准数据类型，DB中保存的是近似值，float类型对应java.lang.Float，不推荐使用',
  `dataTypeDecimal` decimal(10, 2) NOT NULL COMMENT '浮点数值，标准数据类型，以字符串的形式保存数值，decimal类型对应java.lang.BigDecimal，浮点数值推荐用decimal',
  `dataTypeNumeric` numeric NOT NULL COMMENT '整形数值，标准数据类型，8个字节，numeric类型对应java.lang.BigDecimal',

  `dataTypeChar` char(255) NOT NULL COMMENT '定长字符串，char类型对应java.lang.String',
  `dataTypeVarchar` varchar(255) NOT NULL COMMENT '变长字符串，varchar类型对应java.lang.String',

  `dataTypeDate` date NOT NULL COMMENT '日期值，3个字节，date类型对应java.sql.Date',
  `dataTypeTime` time NOT NULL COMMENT '时间值，3个字节，time类型对应java.sql.Time',
  `dataTypeYear` year NOT NULL COMMENT '年份值，1个字节，year类型对应java.sql.Date',
  `dataTypeTimestamp` timestamp NOT NULL COMMENT '日期时间值，4个字节，timestamp类型对应java.sql.Timestamp，记录更新时，如果指定该字段，该字段会自动更新为当前时间；它在MySQL中是以时间戳方式存储，所以就占4个字节',
  `dataTypeDatetime` datetime NOT NULL COMMENT '日期时间值，8个字节，datetime类型对应java.sql.Timestamp',

  `dataTypeTinyblob` tinyblob NOT NULL COMMENT '短二进制字符串，tinyblob类型对应byte[]，4种blob都是同一语义就是长度不同',
  `dataTypeBlob` blob NOT NULL COMMENT '二进制形式的长文本数据，blob类型对应byte[]，使用场景存储图片/视频/音频，但是现在架构已经不推荐将大文件存储在数据库',
  `dataTypeMediumblob` mediumblob NOT NULL COMMENT '二进制形式的中等长度文本数据，mediumblob类型对应byte[]',
  `dataTypeLongblob` longblob NOT NULL COMMENT '二进制形式的极大文本数据，longblob类型对应byte[]',
  `dataTypeTinytext` tinytext NOT NULL COMMENT '短文本字符串，tinytext类型对应java.lang.String，4种text都是同一语义就是长度不同',
  `dataTypeText` text NOT NULL COMMENT '长文本数据，text类型对应java.lang.String，使用场景存储html文本/md文本等等',
  `dataTypeMediumtext` mediumtext NOT NULL COMMENT '中等长度文本数据，mediumtext类型对应java.lang.String',
  `dataTypeLongtext` longtext NOT NULL COMMENT '极大文本数据，longtext类型对应java.lang.String',

  `dataTypeEnum` enum('0','1','2') NOT NULL COMMENT '枚举类型，在规定的集合中多选一，enum类型对应java.lang.String',
  `dataTypeSet` set('0','1','2') NOT NULL COMMENT 'set集合，在规定的集合中多选多，set类型对应java.lang.String',
  `dataTypeBinary` binary(255) NOT NULL COMMENT '定长二进制的字符串，binary类型对应byte[]',
  `dataTypeVarbinary` varbinary(255) NOT NULL COMMENT '变长二进制的字符串，varbinary类型对应byte[]',
  `dataTypePoint` point NOT NULL COMMENT '二维空间的点，point类型对应，使用场景经纬度byte[]',
  `dataTypeLinestring` linestring NOT NULL COMMENT '二维空间的线，linestring类型对应byte[]',
  `dataTypePolygon` polygon NOT NULL COMMENT '二维空间的多边形，polygon类型对应byte[]',
  `dataTypeGeometry` geometry NOT NULL COMMENT '几何类型，geometry类型对应byte[]',
  `dataTypeMultipoint` multipoint NOT NULL COMMENT '多个点，multipoint类型对应byte[]',
  `dataTypeMultilinestring` multilinestring NOT NULL COMMENT '多条线，multilinestring类型对应byte[]',
  `dataTypeMultipolygon` multipolygon NOT NULL COMMENT '多个多边形，multipolygon类型对应byte[]',
  `dataTypeGeometrycollection` geometrycollection NOT NULL COMMENT '多个几何类型，geometrycollection类型对应byte[]'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT='所有数据类型，演示所有MySQL/MariaDB 数据类型和Java数据类型的映射关系，
 主要说明数据类型（数值型，布尔型，字符串等等），占据的字节大小，与java的数据类型映射，注意点。
 有些类型我不常用啊，后面的空间相关的数据类型配合函数使用，算了不深究了。
 好像没有java类与之对应的，就直接是byte[]，不过这样也合理，因为byte[]才是最底层的数据类型，所以自己写转化咯，根据使用的工具自行解决；
 或者直接不使用这些数据类型，使用字符串，然后程序自行解析，这种做法见的多'
;

-- crmAllDataType表的插入，演示所有数据类型的插入，提供具体示例，什么格式，自己有问题可以实验验证自己想法
-- 更多参考见https://www.runoob.com/mysql/mysql-data-types.html
-- point，linestring，polygon，geometry 是2维坐标空间对应相关概念，见https://www.cnblogs.com/JMrLi/p/11548323.html, https://blog.csdn.net/zwhfyy/article/details/89926059
insert into crmAllDataType
(dataTypeTinyint, dataTypeSmallint, dataTypeMediumint, dataTypeInt, dataTypeInteger, dataTypeBigint,
 dataTypeBit, dataTypeReal, dataTypeDouble, dataTypeFloat, dataTypeDecimal, dataTypeNumeric,
 dataTypeChar, dataTypeVarchar,
 dataTypeDate, dataTypeTime, dataTypeYear, dataTypeTimestamp, dataTypeDatetime,
 dataTypeTinyblob, dataTypeBlob, dataTypeMediumblob, dataTypeLongblob, dataTypeTinytext, dataTypeText, dataTypeMediumtext, dataTypeLongtext,
 dataTypeEnum, dataTypeSet, dataTypeBinary, dataTypeVarbinary, dataTypePoint, dataTypeLinestring,
 dataTypePolygon, dataTypeGeometry, dataTypeMultipoint, dataTypeMultilinestring, dataTypeMultipolygon, dataTypeGeometrycollection)
values
(1, 2, 3, 4, 5, 6,
 0, 2.01, 3.01, 4.01, 5.01, 6,
 'abc', 'abc',
 '2020-01-01', '12:00:00', '2020', '2020-01-01 12:00:00', '2020-01-01 12:00:00',
 '123', 'abc', '一二三', '<html></html>', '<html></html>', '<html></html>', '<html></html>', '<html></html>',
 '0', '0,1', 'abc', 'abc123', GeomFromText('POINT(1 1)'), GeomFromText('LINESTRING(1 1, 2 2)'),
 GeomFromText('POLYGON((0 0, 0 1, 1 1, 1 0, 0 0))'),
 GeomFromText('LINESTRING(0 0, 1 0, 1 1, 0 1)'),
 GeomFromText('MULTIPOINT(1 1,2 2,3 3)'),
 GeomFromText('MULTILINESTRING((1 1,2 2,3 3),(2 2,2 3,2 4))'),
 GeomFromText('MULTIPOLYGON(((1 1,1 2,2 2,2 1,1 1)),((2 2,2 3,3 2,2 2)))'),
 GeomFromText('LINESTRING(0 0, 1 0, 1 1, 0 1)')
);


