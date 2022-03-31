if exists(select * from sys.databases where name='Ticket_System')
drop database Ticket_System
go
create database Ticket_System
go
use Ticket_System
GO

IF EXISTS(SELECT * FROM sysobjects WHERE name='USER_LIBRARY' )
DROP TABLE USER_LIBRARY

create table USER_LIBRARY             
 --用户数据表 存储用户名 身份证 电话号码 用户密码 密保密保答案
(
用户名  NVARCHAR(200)   PRIMARY KEY NOT NULL,
用户密码  NVARCHAR(20) NOT NULL,
密保 NVARCHAR(20) NOT NULL,
密保答案 NVARCHAR(20) NOT NULL,
身份证 NVARCHAR(20) NOT NULL,
电话号码 NVARCHAR(20) NOT NULL,
)

select*from USER_LIBRARY  --查询用户数据表



if exists(select * from sysobjects where name='ADMINISTRATOR_LIBARARY')
drop table ADMINISTRATOR_LIBARARY
GO
create table ADMINISTRATOR_LIBARARY       --管理员数据表 存储管理员编号 密码 等级 权限
(
管理员编号 INT  PRIMARY KEY NOT NULL,
管理员密码 NVARCHAR(20) NOT NULL,
等级 NVARCHAR(20) NOT NULL,
)
insert into ADMINISTRATOR_LIBARARY(管理员编号,管理员密码,等级) --插入数据到管理员数据表
SELECT 10086,'ljc10086','普通管理员'UNION
SELECT 10087,'ljc10087','一般管理员' UNION
SELECT 10088,'ljc10088','优秀管理员'UNION
SELECT 10089,'ljc10089','超级管理员'

select*from ADMINISTRATOR_LIBARARY


if exists (select 1 from sysobjects where id = object_id('TICKET_INFORMATION'))
drop table TICKET_INFORMATION
go
create table TICKET_INFORMATION    --列车数据表 列车号 列车长 起始站 出发时间 终点站 到站时间 日期 联系电话
(
列车号 NVARCHAR(20) PRIMARY KEY ,
列车长 NVARCHAR(20),
起始站 NVARCHAR(20) NOT NULL,
出发时间 Time(0) NOT NULL,
终点站 NVARCHAR(20) NOT NULL,
到站时间 Time(0) NOT NULL,
日期 DATE ,
联系电话 NVARCHAR(20)
)
insert into TICKET_INFORMATION(列车号,列车长,起始站,出发时间,终点站,到站时间,日期,联系电话) --插入数据到列车数据表
SELECT 'G141','刘健成','北京','14:10','上海','20:10','20210602','110' UNION
SELECT 'G145','刘健成','北京','14:35','上海','20:34','20210602','110' UNION
SELECT 'G155','刘健成','北京','15:45','上海','21:41','20210602','110' UNION
SELECT 'G79','刘健成','北京','10:00','广州','18:05','20210602','110'  UNION
SELECT 'G69','刘健成','北京','13:07','广州','22:28','20210602','110'  UNION
SELECT 'G39','刘健成','北京','13:07','广州','22:28','20211025','110'  UNION
SELECT 'C31','刘健成','北京','13:48','广州','19:48','20210702','110'  UNION
SELECT 'K507','刘健成','北京','07:12','重庆','08:59','20210702','110' UNION
SELECT 'G309','刘健成','北京','08:18','重庆','20:21','20210702','110' UNION
SELECT 'G571','刘健成','北京','09:22','重庆','20:34','20210702','110' UNION
SELECT 'G89','刘健成','北京','06:53','成都','14:38','20210602','110'  UNION
SELECT 'K817','刘健成','北京','08:01','成都','12:32','20210602','110' UNION
SELECT 'G485','刘健成','北京','05:14','长沙','14:06','20210602','110' UNION
SELECT 'C2551','刘健成','北京','06:00','天津','06:30','20210602','110'UNION
SELECT 'D29','刘健成','北京','06:34','哈尔滨','14:39','20210602','110'UNION
SELECT 'G102','刘健成','上海','06:26','北京','12:29','20210602','110' UNION
SELECT 'G85','刘健成','上海','08:00','广州','14:51','20210602','110'  UNION
SELECT 'D636','刘健成','上海','06:32','重庆','18:36','20210602','110' UNION
SELECT 'G1974','刘健成','上海','07:17','成都','18:27','20210602','110'UNION
SELECT 'G1975','刘健成','上海','19:17','长沙','22:57','20210602','110'UNION
SELECT 'G1976','刘健成','上海','11:17','天津','18:47','20210602','110'UNION
SELECT 'G197','刘健成','上海','10:17','哈尔滨','18:07','20210602','110'


select *from TICKET_INFORMATION


if exists (select 1 from sysobjects where id = object_id('TICKET_LEVEL'))
drop table TICKET_LEVEL
go
create table TICKET_LEVEL   --车票数据表 列车号 商务票数 一等票数 二等票数 无座票数 商务票价 一等票价 二等票价 无座票价
(
列车号 NVARCHAR(20)   PRIMARY KEY NOT NULL,
商务票数    int constraint CK_sex11 check(商务票数>=0),--票数大于0
一等票数    int constraint CK_sex12 check(一等票数>=0),--票数大于0
二等票数    int constraint CK_sex13 check(二等票数>=0),--票数大于0
无座票数    int constraint CK_sex14 check(无座票数>=0),--票数大于0
商务票价 INT NOT NULL,
一等票价 INT NOT NULL,
二等票价 INT NOT NULL,
无座票价 INT NOT NULL,
)
insert into TICKET_LEVEL(列车号,商务票数,一等票数,二等票数,无座票数,商务票价,一等票价,二等票价,无座票价)  --插入数据到车票数据表
SELECT 'G141',53,21,41,32,324.31,123.4,110.3,49.52 UNION
SELECT 'G145',25,25,75,34,125.44,112.4,100.3,50.54 UNION
SELECT 'G155',21,67,45,24,224.53,111.4,97.3,51.61  UNION
SELECT 'G79', 65,52,64,32,641.51,123.4,110.3,61.24 UNION
SELECT 'G69', 45,23,31,51,254.64,123.4,110.3,21.28 UNION
SELECT 'G39', 53,21,41,31,624.25,123.4,110.3,64.52 UNION
SELECT 'C31', 31,31,13,46,125.71,123.4,110.3,21.54 UNION
SELECT 'K507',65,64,31,32,621.64,23.4,110.3,64.64 UNION
SELECT 'G309',13,76,76,65,482.32,123.4,110.3,24.25 UNION
SELECT 'G571',35,31,32,32,624.45,123.4,110.3,41.45 UNION
SELECT 'G89', 31,32,45,52,125.16,123.4,110.3,34.46 UNION
SELECT 'K817',67,97,31,78,675.14,123.4,110.3,58.65 UNION
SELECT 'G485',34,43,52,61,645.2,123.4,110.3,84.13 UNION
SELECT 'C2551',52,13,24,61,714.35,123.4,110,36.65 UNION
SELECT 'D29', 64,76,20,33,164.65,123.4,110.3,35.64 UNION
SELECT 'G102',52,31,24,32,185.52,123.4,110.3,74.64 UNION
SELECT 'G85',153,21,41,56,484.64,123.4,64.3,57.78 UNION
SELECT 'D636',53,61,64,68,542.46,123.4,58.3,24.75 UNION
SELECT 'G1974',3,65,41,20,461.32,123.4,82.3,57.16 UNION
SELECT 'G1975',9,57,64,10,542.46,123.4,87.3,64.2 UNION
SELECT 'G1976',4,50,81,45,642.82,123.4,74.3,57.5 UNION
SELECT 'G197',53,31,85,84,654.32,123.4,126.3,49.64 

select*from TICKET_LEVEL

IF EXISTS(SELECT * FROM sysobjects WHERE name='TICKER_BUYER' )
DROP TABLE TICKER_BUYER
create table TICKER_BUYER              --购票人数据表 存储用户名  列车号 商务票数 一等票数 二等票数 无座票数
(
用户名  NVARCHAR(200)  PRIMARY KEY NOT NULL,
列车号  NVARCHAR(20) NOT NULL,
起始站  NVARCHAR(20) NOT NULL,
终点站  NVARCHAR(20) NOT NULL,
商务票数    int DEFAULT 0 constraint CK_sex check(商务票数>=0) ,--票数大于0 
一等票数    int DEFAULT 0 constraint CK_sex1 check(一等票数>=0),--票数大于0
二等票数    int DEFAULT 0 constraint CK_sex2 check(二等票数>=0),--票数大于0
无座票数    int DEFAULT 0 constraint CK_sex3 check(无座票数>=0),--票数大于0
)

select *from TICKER_BUYER


IF EXISTS (SELECT * FROM sysobjects WHERE name = 'view_TICKET_INFORMATION_TICKET_LEVEL_GLY')  
--创建视图，用于显示车站列车信息的视图
DROP VIEW view_TICKET_INFORMATION_TICKET_LEVEL_GLY
GO
CREATE VIEW view_TICKET_INFORMATION_TICKET_LEVEL_GLY
AS
SELECT 列车号=TICKET_INFORMATION.列车号,列车长 = 列车长,日期=日期,起始站=起始站,终点站=终点站,
出发时间=出发时间,到站时间=到站时间,商务票数=商务票数,一等票数=一等票数,二等票数=二等票数,
无座票数=无座票数,商务票价=商务票价,一等票价=一等票价,
二等票价=二等票价,无座票价=无座票价,联系电话=联系电话
FROM TICKET_INFORMATION LEFT JOIN TICKET_LEVEL 
ON TICKET_INFORMATION.列车号=TICKET_LEVEL.列车号
GO


SELECT * FROM view_TICKET_INFORMATION_TICKET_LEVEL_GLY   

IF EXISTS (SELECT * FROM sysobjects WHERE name = 'view_TICKET_INFORMATION_TICKET_LEVEL')  

DROP VIEW view_TICKET_INFORMATION_TICKET_LEVEL
GO
CREATE VIEW view_TICKET_INFORMATION_TICKET_LEVEL
AS
SELECT 列车号=TICKET_INFORMATION.列车号,日期=日期,出发时间=出发时间,
到站时间=到站时间,商务票数=商务票数,一等票数=一等票数,二等票数=二等票数,
无座票数=无座票数,商务票价=商务票价,一等票价=一等票价,
二等票价=二等票价,无座票价=无座票价,起始站=起始站,终点站=终点站
FROM TICKET_INFORMATION LEFT JOIN TICKET_LEVEL 
ON TICKET_INFORMATION.列车号=TICKET_LEVEL.列车号
GO


SELECT * FROM view_TICKET_INFORMATION_TICKET_LEVEL