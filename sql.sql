create database if not exists ExSM;

drop table `exercise`;
create table if not exists `exercise`(
	`id` int auto_increment,
    `type` varchar(40) not null,
    `date` datetime default current_timestamp on update current_timestamp,
    `score` int,
    `info` text,
    `ans` text,
    `cour_name` varchar(40), 
    `ch_name` varchar(150),
    `times` int default 0,
    primary key(`id`),
    foreign key(`cour_name`) references `course`(`name`) on update cascade on delete cascade);
insert into exercise(id, type, score, info, ans, cour_name, ch_name)
	values(1, "填空题", 2, "   为关系数据库语言国际标准语言", "SQL", "数据库", "绪论");
insert into exercise(type, score, info, ans, cour_name, ch_name) values
	("选择题", 1, "模式和内模式（）\nA.只能各有一个  B.最多只能有一个\nC.至少两个  D.可以有多个", "A", "数据库", "绪论"),
    ("选择题", 1, "在数据库中存储的是（）\nA.数据  B.信息\nC.数据和数据之间的联系  D.数据模型的定义", "C", "数据库", "绪论"),
    ("填空题", 2, "数据库就是长期储存在计算机内    、     的数据集合", "有组织 可共享", "数据库", "绪论"),
    ("填空题", 2, "关系中主码的取值必须唯一且非空，这条规则是     完整性规则", "实体",  "数据库", "关系数据库");
select * from exercise;
alter table exercise drop primary key;
alter table exercise add constraint foreign key (ch_name, cour_name) references charpter(name, cour_name) on update cascade on delete cascade;
show create table exercise;

drop table course;
desc course;
create table if not exists `course`(
	`name` varchar(40) not null,
    `ex_num` int default 0,
    primary key(`name`));
insert into course values
	("数据库", 10), ("c++", 10);
select * from course;
update course set ex_num = ex_num - 1 where name in (select cour_name from exercise where exercise.id = 1);
update course set name = "algorithm" where name = "算法";

create table if not exists `charpter`(
	`name` varchar(150) not null,
    `cour_name` varchar(40) not null,
	`ex_num` int default 0,
    primary key(`name`, `cour_name`),
    foreign key(`cour_name`) references course(name) on update cascade on delete cascade);
insert into charpter(name, cour_name) values
	("绪论", "数据库");
insert into charpter(name, cour_name) values
	("关系数据库", "数据库"), ("关系数据库标准语言SQL","数据库"), ("关系数据库设计理论", "数据库"),
    ("数据库安全保护", "数据库"), ("数据库设计", "数据库");
select * from charpter;
insert into charpter(name, cour_name) values
	("绪论", "算法");
select count(*) from charpter, course where course.name = "数据库" and course.id = charpter.cour_id;


drop table type;
select * from type;
create table if not exists `type`(
	`name` varchar(50) not null,
	`cour_name` varchar(40) not null,
    `ex_num` int,
    primary key(`name`,`cour_name`),
    foreign key(`cour_name`) references course(name) on update cascade on delete cascade);
insert into type values
	("选择题", "数据库", 5), ("填空题", "数据库", 5);
alter table type change ex_num ex_num int default 0;



select * from exercise;
select * from course;
select * from type;
select * from charpter;

