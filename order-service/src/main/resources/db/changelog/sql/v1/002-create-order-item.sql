create table order_items (
    id bigint generated always as identity primary key ,
    product_id bigint not null ,
    product_name varchar(128) not null ,
    amount int not null,
    price numeric(10, 2) not null ,
    order_id bigint not null references orders
)