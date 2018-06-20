create database if not exists ExSM;

drop table `exercise`;
create table if not exists `exercise`(
	`id` int auto_increment,
    `type` varchar(40) not null,
    `date` datetime,
    `score` int,
    `info` text,
    `ans` text,
    `cour_id` int, 
    `ch_id` int,
    `times` int default 0,
    primary key(`id`));
alter table exercise change date date datetime default current_timestamp on update current_timestamp;
insert into exercise(id, type, score, info, ans, cour_id, ch_id)
	values(1, "填空题", 2, "   为关系数据库语言国际标准语言", "SQL", 1, 1);
insert into exercise(type, score, info, ans, cour_id, ch_id) values
	("选择题", 1, "模式和内模式（）\nA.只能各有一个  B.最多只能有一个\nC.至少两个  D.可以有多个", "A", 1, 1),
    ("选择题", 1, "在数据库中存储的是（）\nA.数据  B.信息\nC.数据和数据之间的联系  D.数据模型的定义", "C", 1, 1),
    ("填空题", 2, "数据库就是长期储存在计算机内    、     的数据集合", "有组织 可共享", 1, 1),
    ("填空题", 2, "关系中主码的取值必须唯一且非空，这条规则是     完整性规则", "实体",  1, 2);

drop table course;
desc course;
create table if not exists `course`(
	`name` varchar(40) not null,
    `id` int auto_increment not null,
    `ex_num` int,
    primary key(`id`));
    
insert into course values
	("数据库", 1, 10), ("c++", 2, 10);

create table if not exists `charpter`(
	`name` varchar(150) not null,
	`id` int auto_increment,
    `cour_id` int not null,
	`ex_num` int,
    primary key(`id`),
    foreign key(`cour_id`) references `course`(`id`) on update cascade on delete cascade);
alter table charpter drop primary key;
alter table charpter change id id int;
alter table charpter add primary key(`id`, `cour_id`);
insert into charpter values
	("绪论", 1, 1, 10);
insert into charpter values
	("关系数据库", 2, 1, 5), ("关系数据库标准语言SQL", 3, 1, 5), ("关系数据库设计理论", 4, 1, 5),
    ("数据库安全保护", 5, 1, 5), ("数据库设计", 6, 1, 5);

select count(*) from charpter, course where course.name = "数据库" and course.id = charpter.cour_id;



create table if not exists `type`(
	`name` varchar(50) not null,
	`cour_id` int,
    `ex_num` int,
    primary key(`name`,`cour_id`),
    foreign key(`cour_id`) references course(id) on update cascade on delete cascade);
insert into type values
	("选择题", 1, 5), ("填空题", 1, 5);


