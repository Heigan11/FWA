drop table if exists users;
CREATE TABLE users(
                      id SERIAL PRIMARY KEY,
                      name varchar(50) not null,
                      surname varchar(50) not null,
                      phone varchar(50) unique not null,
                      email varchar(50) not null,
                      password varchar(100) not null);