create table constellation(
	id serial primary key,
	name varchar(255)
);

create table star(
	id serial primary key,
	name varchar(255),
	age bigint,
	constellation_id int references constellation(id)
);

insert into constellation (name) values ('orion');
insert into star (name, age, constellation_id) values ('a orion', 10010000, 1);

select * from star;
select * from constellation where id in (select id from star);