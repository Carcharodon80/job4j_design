--Даны две сущности, представленные в таблицах departments и emploers.
--Отношение one-to-many. В таблицах только поле name
create table departments (
	id serial primary key,
	name varchar(255)
);

create table employers (
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments (name) values ('Secretary');
insert into departments (name) values ('Builders');
insert into departments (name) values ('Machinery');
insert into departments (name) values ('Managers');

insert into employers (name, department_id) values ('Kate', 1);
insert into employers (name, department_id) values ('Maria', 1);
insert into employers (name, department_id) values ('Nastya', 1);
insert into employers (name, department_id) values ('Michail', 2);
insert into employers (name, department_id) values ('Alex', 2);
insert into employers (name, department_id) values ('Robot_1', 3);
insert into employers (name, department_id) values ('Robot_2', 3);
insert into employers (name, department_id) values ('Robot_3', 3);
insert into employers (name, department_id) values ('Robot_4', 3);

--Выполнить запросы с left, rigth, full, cross соединениями
select * from employers left join departments on employers.department_id = departments.id;
select * from employers right join departments on employers.department_id = departments.id;
select * from employers full join departments on employers.department_id = departments.id;
select * from employers cross join departments;

--Используя left join найти департаменты, у которых нет работников
select * from departments left join employers 
	on employers.department_id = departments.id
	where employers.id is null; 
	
--Используя left и right join написать запросы, которые давали бы одинаковый результат
select employers.name, departments.name from departments left join employers 
	on employers.department_id = departments.id;
select employers.name, departments.name from employers right join departments 
	on employers.department_id = departments.id; 
	
--Создать таблицу teens с атрибутами name, gender и заполнить ее
create table teens (
	name varchar(255),
	gender varchar(5)
);

insert into teens (name, gender) values ('Kate', 'woman');
insert into teens (name, gender) values ('Sergey', 'man');
insert into teens (name, gender) values ('Maria', 'woman');
insert into teens (name, gender) values ('Marina', 'woman');
insert into teens (name, gender) values ('Mike', 'man');


--Используя cross join составить все возможные разнополые пары
select teen1.name as "boy", teen2.name as "girl" 
	from teens teen1 cross join teens teen2
	where teen1.gender = 'man' 
	and teen1.gender != teen2.gender;