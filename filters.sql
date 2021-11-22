create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
	);
	
insert into type (name) values ('сыр');
insert into type (name) values ('мясо');
insert into type (name) values ('молоко');
insert into type (name) values ('хлеб');
insert into type (name) values ('десерт');

insert into product (name, expired_date, price, type_id) values ('брынза', '2021-12-31', 15.75, 1);
insert into product (name, expired_date, price, type_id) values ('моцарелла', '2021-11-01', 10.6, 1);
insert into product (name, expired_date, price, type_id) values ('маасдам', '2021-11-30', 25.0, 1);
insert into product (name, expired_date, price, type_id) values ('говядина', '2021-12-25', 170, 2);
insert into product (name, expired_date, price, type_id) values ('баранина', '2021-12-30', 250.52, 2);
insert into product (name, expired_date, price, type_id) values ('молоко коровье', '2021-11-30', 34.5, 3);
insert into product (name, expired_date, price, type_id) values ('молоко козье', '2021-11-01', 37.3, 3);
insert into product (name, expired_date, price, type_id) values ('кефир', '2021-10-31', 30.7, 3);
insert into product (name, expired_date, price, type_id) values ('бородинский', '2021-11-29', 15, 4);
insert into product (name, expired_date, price, type_id) values ('мороженое пломбир', '2022-05-31', 25.6, 5);
insert into product (name, expired_date, price, type_id) values ('мороженое эскимо', '2022-06-01', 26, 5);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product 
	join type
	on product.type_id = type.id
	and type.name = 'сыр';
	
-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"	
select * from product 
	where product.name like '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product 
	where product.expired_date < current_date;

-- 4. Написать запрос, который выводит самый дорогой продукт.
select * from product
	where price = (
		select max(price) from product
	); 
	
-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select type.name, count(type.name) 
	from product
	join type
	on product.type_id = type.id
	group by type.name;
	
-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product
	join type
	on product.type_id = type.id
	where type.name = 'сыр' 
	or type.name = 'молоко';
	
-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.	
select type.name, count(type.name) 
	from product
	join type
	on product.type_id = type.id
	group by type.name
	having count(type.name) < 10;

-- 8. Вывести все продукты и их тип.
select product.name, type.name
	from product 
	join type
	on product.type_id = type.id;
