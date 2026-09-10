create table categories (
    id bigint generated always as identity primary key ,
    category_name varchar(128) not null unique
)