create table orders (
    id bigint generated always as identity primary key ,
    user_id int not null ,
    total_price numeric(10, 2)
)