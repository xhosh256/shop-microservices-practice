create table products (
    id bigint generated always as identity primary key ,
    product_name varchar(128) not null unique ,
    price numeric(10, 2) not null ,
    category_id bigint references categories
);