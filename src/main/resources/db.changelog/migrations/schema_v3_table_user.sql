-- liquibase formatted sql

-- changeset Andrey:1

CREATE TABLE t1_ss_jwt.users (
id bigserial PRIMARY KEY,
username VARCHAR(255) UNIQUE NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
role VARCHAR(255) NOT NULL
);
