insert into roles (role) values ('fictional');
insert into roles (role) values ('real');

insert into users (name, role_id) values ('Ded Moroz', 1);
insert into users (name, role_id) values ('Gendalf', 1);
insert into users (name, role_id) values ('Vasya Muhin', 2);

insert into rules (rule) values ('can do anything');
insert into rules (rule) values ('can do something');

insert into roles_rules (role_id, rule_id) values (1, 1);
insert into roles_rules (role_id, rule_id) values (2, 2);

insert into categories (category) values ('very important');
insert into categories (category) values ('important');
insert into categories (category) values ('not important');

insert into states (state) values ('in process');
insert into states (state) values ('finished');

insert into items (item, user_id, category_id, state_id) values ('Start New Year', 1, 1, 1);
insert into items (item, user_id, category_id, state_id) values ('Save the hobbits', 2, 2, 1);
insert into items (item, user_id, category_id, state_id) values ('Fix the chair', 3, 3, 1);

insert into comments (comment, item_id) values ('Hurry up!', 1);
insert into comments (comment, item_id) values ('Fuck it', 3);
