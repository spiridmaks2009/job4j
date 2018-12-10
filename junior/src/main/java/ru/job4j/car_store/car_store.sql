create database carstore;

create table carbody(
    id serial primary key,
    name varchar(2000),
    color varchar(2000)
);

create table engine(
    id serial primary key,
    name varchar(2000),
    power double
);

create table transmission(
    id serial primary key,
    name varchar(2000),
    automatic boolean
);

create table car(
    id serial primary key,
    name varchar(2000),
    carbody_id int references carbody(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);

insert into carbody(name, color) values('body1', 'red');
insert into carbody(name, color) values('body2', 'black');
insert into carbody(name, color) values('body3', 'white');
insert into carbody(name, color) values('body4', 'blue');
insert into engine(name, power) values('engine1', 300);
insert into engine(name, power) values('engine2', 200);
insert into engine(name, power) values('engine3', 350);
insert into engine(name, power) values('engine4', 250);
insert into transmission(name, automatic) values('automatic', true);
insert into transmission(name, automatic) values('manual', false);
insert into car(name, carbody_id, engine_id, transmission_id) values('bmw', 2, 3, 1);
insert into car(name, carbody_id, engine_id, transmission_id) values('vaz', 3, 2, 2);
insert into car(name, carbody_id, engine_id, transmission_id) values('mazda', 1, 4, 1);

1. Вывести список всех машин и все привязанные к ним детали.
select c.name as car, b.name as body, e.name as enginr, t.name as transmission
from car as c
left join carbody as b on b.id = c.carbody_id
left join engine as e on e.id = c.engine_id
left join transmission as t on t.id = c.transmission_id;


2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

select b.name from car as c
right join carbody as b on c.carbody_id = b.id
where c.name is null;

select e.name from car as c
right join engine as e on c.engine_id = e.id
where c.name is null;

select t.name from car as c
right join transmission as t on c.transmission_id = t.id
where c.name is null;