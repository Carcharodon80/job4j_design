create table fauna (
	id serial primary key,
	name text,
	avg_age int,
	discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('old_fish', 100, '01.08.1950');
insert into fauna (name, avg_age, discovery_date) values ('new_spider', 15000, '01.08.1900');
insert into fauna (name, avg_age, discovery_date) values ('yellow_fish', 10000, null);
insert into fauna (name, avg_age, discovery_date) values ('dumb_deer', 10000, '24.11.1930');
insert into fauna (name, avg_age, discovery_date) values ('angry_hedgenog', 5000, null);
insert into fauna (name, avg_age, discovery_date) values ('tiny_elephant', 1000000, '01.01.1800');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';