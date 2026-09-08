create table profiles (
    id int generated always as identity primary key,
    firstname varchar(128) not null ,
    lastname varchar(128) ,
    user_id int not null unique references users
)