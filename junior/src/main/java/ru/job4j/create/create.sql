
user - role = many-to-one
role - rules = many-to-many
item - user = one-to-one
item - comments = one-to-many
item - attachs = one-to-many
item - category = many-to-one
item - state = many-to-one

create database requests;

create table category(
    id serial primary key,
    name_category varchar(2000)
);

create table state(
    id serial primary key,
    name_state varchar(2000)
);

create table users(
    id serial primary key,
    name varchar(2000)
    --item_id int references item(id)
);

create table role(
    id serial primary key,
    name_role varchar(2000),
    user_id int references users(id)
);

create table rules(
    id serial primary key,
    name_rule varchar(2000)
);

create table role_rules(
    id serial primary key,
    id_role int references role(id),
    id_rule int references rules(id)
);

create table item(
    id serial primary key,
    item_name varchar(2000),
    user_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);

alter table users
add foreign key (item_id) references item (id);

create table comments(
    id serial primary key,
    text_comment text,
    item_id int references item(id)
);

create table attachs(
    id serial primary key,
    path varchar(2000),
    item_id int references item(id)
);

