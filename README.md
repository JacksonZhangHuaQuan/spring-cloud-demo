# 项目简介

项目使用springMVC结合JdbcTemplate，完成了对一个表进行增删改查的基本操作，并且采用restfulAPI的方式进行访问。

# SQL

```sql

CREATE TABLE `information` (
  `ID` varchar(38) NOT NULL,
  `TITLE` varchar(255) NOT NULL,
  `INFO` text,
  `STATUS` int(11) DEFAULT '0',
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATOR` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES ('019af7bd17589c71d94c549068a00209', 'test1', 'test1 info test', '1', '2018-08-07 11:20:31', 'baiyh');
INSERT INTO `information` VALUES ('qwdqwdqwd', 'test', 'qdw', '0', '2018-08-06 00:00:00', 'bai');

```

建好表后，在项目的resources目录下的jdbc.properties目录下修改数据库配置
