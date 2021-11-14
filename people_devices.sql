create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('Nokia', 15500);
insert into devices (name, price) values ('Samsung', 17000);
insert into devices (name, price) values ('Pixel', 2500);
insert into devices (name, price) values ('Xiaomi', 7000);
insert into devices (name, price) values ('LG', 3000);

insert into people (name) values ('John');
insert into people (name) values ('Maria');
insert into people (name) values ('Ivan');
insert into people (name) values ('Ded Mazay');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (2, 1);
insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (3, 2);
insert into devices_people (device_id, people_id) values (5, 3);

select avg(price) from devices;

select people.name, avg(price) 
from people
join devices_people on people.id = devices_people.people_id
join devices on devices_people.device_id = devices.id 
group by people.name;

select people.name, avg(price) 
from people
join devices_people on people.id = devices_people.people_id
join devices on devices_people.device_id = devices.id 
group by people.name
having avg(price) > 5000;