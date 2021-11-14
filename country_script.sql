create table countries(
	id serial primary key,
	name varchar(255),
	area varchar(50)
);

create table cities (
	id serial primary key,
	name varchar(255),
	country_id int references countries(id)
);

insert into countries (name, area) values ('Canada', 'North America');
insert into countries (name, area) values ('France', 'Europe');
insert into countries (name, area) values ('Australia', 'Australia');
insert into countries (name, area) values ('Egypt', 'Africa');
insert into countries (name, area) values ('Germany', 'Europe');
insert into countries (name, area) values ('Tunis', 'Africa');

insert into cities (name, country_id) values ('Toronto', 1);
insert into cities (name, country_id) values ('Ottawa', 1);
insert into cities (name, country_id) values ('Sidney', 3);
insert into cities (name, country_id) values ('Cairo', 4);
insert into cities (name, country_id) values ('Berlin', 5);
insert into cities (name, country_id) values ('Munich', 5);
insert into cities (name, country_id) values ('Paris', 2);
insert into cities (name, country_id) values ('Lion', 2);
insert into cities (name, country_id) values ('Tunis', 6); 

select Страна.name, Страна.area, Город.name 
from countries as Страна 
join cities as Город 
on Город.country_id = Страна.id 
and Страна.area = 'Europe';

select countries.name as Страна, area as Материк, cities.name as Город 
from countries 
join cities 
on countries.id = cities.country_id;

select countries.name as Страна_Город, area as Материк 
from countries 
join cities 
on countries.name = cities.name;