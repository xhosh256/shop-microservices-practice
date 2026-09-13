create table credentials (
    id int generated always as identity primary key ,
    username varchar(128) not null unique ,
    password varchar(128) not null ,
    role varchar(128) not null
)