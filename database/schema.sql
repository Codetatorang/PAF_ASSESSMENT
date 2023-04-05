create database railway;

use railway;

create table user(
	id int not null auto_increment,	
	user_id varchar(8) not null,
	username varchar(50) not null,
	name varchar(50) not null,
	constraint pk_id primary key(id),
	constraint unique_user_id unique (user_id)
);

select * from user;

create table task(
	task_id int not null auto_increment,
	description varchar(255),
	priority int not null,
	constraint priority_limit check (priority >= 1 AND priority <=3),
	due_date date not null,
	user_id varchar(8) not null,
	constraint pk_task_id PRIMARY KEY (task_id),
	constraint fk_user_id FOREIGN KEY (user_id) REFERENCES user(user_id)
);

select * from task;