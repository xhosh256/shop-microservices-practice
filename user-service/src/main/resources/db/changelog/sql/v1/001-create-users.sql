create table users (
    id int generated always as identity primary key ,
    username varchar(128) not null unique
);