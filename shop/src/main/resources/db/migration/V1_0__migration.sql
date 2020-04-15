create schema SHOP_SCHEMA;
create table if not exists SHOP_SCHEMA.customer(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    email_address VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    user_name VARCHAR(255)
);

create table if not exists SHOP_SCHEMA.location(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    address_city VARCHAR(255),
    address_country VARCHAR(255),
    address_county VARCHAR(255),
    address_street_address VARCHAR(255),
    name VARCHAR(255)
);

create table if not exists SHOP_SCHEMA.product_category(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255)
);

create table if not exists SHOP_SCHEMA.supplier(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

create table if not exists SHOP_SCHEMA.revenue(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    sum DECIMAL(19, 2),
    location_id INTEGER,
    FOREIGN KEY (location_id) REFERENCES location
);

create table if not exists SHOP_SCHEMA.product(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    image_url VARCHAR(255),
    name VARCHAR(255),
    price DECIMAL(19, 2),
    weight DOUBLE,
    product_category_id INTEGER,
    supplier_id INTEGER,
    FOREIGN KEY (product_category_id) REFERENCES product_category,
    FOREIGN KEY (supplier_id) REFERENCES supplier
);

create table if not exists SHOP_SCHEMA.stock(
    stock_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    quantity INTEGER,
    location_id INTEGER,
    product_id INTEGER,
    FOREIGN KEY (location_id) REFERENCES location,
    FOREIGN KEY (product_id) REFERENCES product
);

create table if not exists SHOP_SCHEMA.orders(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    address_city VARCHAR(255),
    address_country VARCHAR(255),
    address_county VARCHAR(255),
    address_street_address VARCHAR(255),
    local_date_time TIMESTAMP,
    customer_id INTEGER,
    shipped_form_id INTEGER,
    FOREIGN KEY (shipped_form_id) REFERENCES location,
    FOREIGN KEY (customer_id) REFERENCES customer
);

create table if not exists SHOP_SCHEMA.order_detail(
    order_detail_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    quantity INTEGER,
    order_id INTEGER,
    product_id INTEGER,
    FOREIGN KEY (order_id) REFERENCES orders,
    FOREIGN KEY (product_id) REFERENCES product
);