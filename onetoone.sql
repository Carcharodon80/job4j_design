create table countries (
	id serial primary key,
	name varchar(255),
	materic varchar(50),
	population int
);

create table capitals (
	id serial primary key,
	name varchar(255),
	population int
);

create table countries_capitals (
	id serial primary key,
	country_id int references countries(id) unique,
	capital_id int references capitals(id) unique
);

insert into countries (name, materic, population) values ('France', 'Europe', 67000000);
insert into countries (name, materic, population) values ('Canada', 'North America', 38000000);
insert into countries (name, materic, population) values ('Egypt', 'Africa', 100000000);

insert into capitals (name, population) values ('Cairo', 9000000);
insert into capitals (name, population) values ('Paris', 2000000);
insert into capitals (name, population) values ('Ottawa', 1000000);


insert into countries_capitals (country_id, capital_id) values (1, 2);
insert into countries_capitals (country_id, capital_id) values (2, 3);
insert into countries_capitals (country_id, capital_id) values (3, 1);