-- liquibase formatted sql

-- changeset Andrey:1

insert into t1_ss_jwt.person
      (name, surname, age, number_phone, city_of_living)
values('Bob', 'Petrov', 30, '8-922-444-67-56', 'VLG'),
      ('alexey', 'Bobrov', 20, '8-922-333-67-56', 'Moscow'),
      ('Oly', 'Petrova', 19, '8-922-222-67-56', 'Moscow'),
      ( 'Oly', 'Popova', 47, '8-999-888-77-66', 'Yfa' );