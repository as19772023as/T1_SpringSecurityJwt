-- liquibase formatted sql

-- changeset Andrey:1

create table if not exists t1_ss_jwt.person
(
id_person bigserial primary key,
name varchar(100) not null,
surname varchar(150) not null,
age int not null,
number_phone varchar(30) not null,
city_of_living varchar(200) not null
);