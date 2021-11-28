create table shops (
	id serial primary key,
	name varchar(255),
	address text
);

create table books (
	id serial primary key,
	name varchar(255),
	author varchar(255)
);

create table shops_books (
	id serial primary key,
	shop_id int references shops(id),
	book_id int references books(id)
);

insert into shops (name, address) values ('Biblio', 'Moscow');
insert into shops (name, address) values ('Book-Ok', 'Kaluga');
insert into shops (name, address) values ('Bookmate', 'Online');

insert into books (name, author) values ('Idiot', 'Dostoevsky');
insert into books (name, author) values ('To kill a mockingbird', 'Harper Lee');
insert into books (name, author) values ('Jane Eyre', 'Charlotte Bronte');

insert into shops_books (shop_id, book_id) values (1, 1);
insert into shops_books (shop_id, book_id) values (2, 2);
insert into shops_books (shop_id, book_id) values (2, 3);
insert into shops_books (shop_id, book_id) values (3, 1);
insert into shops_books (shop_id, book_id) values (3, 2);
insert into shops_books (shop_id, book_id) values (3, 3);

