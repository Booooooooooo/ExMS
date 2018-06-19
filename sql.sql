create database if not exists ExSM;

create table if not exists `exercise`(
	`id` int auto_increment,
    `type` varchar(40) not null,
    `date` datetime,
    `score` int,
    `info` text,
    `ans` text,
    primary key(`id`))
    
create table if not exists `course`(
	`name` varchar(40) not null,
    `