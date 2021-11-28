create table body (
	id serial primary key,
	name varchar(255)
);

create table engine (
	id serial primary key,
	name varchar(255)
);

create table transmission (
	id serial primary key,
	name varchar(255)
);

create table car (
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body (name) values ('bmw_sedan');
insert into body (name) values ('bmw_coupe');
insert into body (name) values ('audi_sedan');
insert into body (name) values ('lada_sedan');

insert into engine (name) values ('bmw_engine');
insert into engine (name) values ('opel_engine');
insert into engine (name) values ('audi_engine');
insert into engine (name) values ('lada_engine');

insert into transmission (name) values ('audi_trans');
insert into transmission (name) values ('kamaz_trans');
insert into transmission (name) values ('bmw_trans');

insert into car (name, body_id, engine_id, transmission_id) values ('bmw_s', 1, 1, 3);
insert into car (name, body_id, engine_id, transmission_id) values ('bmw_c', 2, 1, 3);
insert into car (name, body_id, engine_id, transmission_id) values ('audi', 3, 3, 1);

--Вывести список всех машин и все привязанные к ним детали.
select car.name as "Car", body.name as "Body", engine.name as "Engine", transmission.name as "Transmission" from car 
	join body on car.body_id = body.id
	join engine on car.engine_id = engine.id
	join transmission on car.transmission_id = transmission.id;
	
--Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине
select body.name as "Unused body" from car 
	right join body on car.body_id = body.id
	where body_id is null;
	
select engine.name as "Unused engine" from car 
	right join engine on car.engine_id = engine.id
	where engine_id is null;
	
select transmission.name as "Unused transmission" from car 
	right join transmission on car.transmission_id = transmission.id
	where transmission_id is null;