create table films(
	id serial primary key,
	name varchar(255),
	lenght int,
	genre text
);

insert into films(name, genre, lenght) values ('Apocalypse now', 'drama', 204);

select * from films;

update films set genre = 'war drama';

delete from films;

select * from films;
