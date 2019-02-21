CREATE SEQUENCE city_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE customer_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE motoboy_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE restaurant_type_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE order_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE city (
  id   BIGINT NOT NULL,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE customer (
  id        BIGINT NOT NULL,
  latitude  DECIMAL(10, 8),
  longitude DECIMAL(10, 8),
  PRIMARY KEY (id)
);

CREATE TABLE motoboy (
  id        BIGINT NOT NULL,
  latitude  DECIMAL(10, 8),
  longitude DECIMAL(10, 8),
  PRIMARY KEY (id)
);

CREATE TABLE product (
  id            VARCHAR(255) NOT NULL,
  category      VARCHAR(255),
  description   VARCHAR(255),
  unit_price    DECIMAL(10, 8),
  city_id       BIGINT,
  restaurant_id VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE restaurant (
  id                 VARCHAR(255) NOT NULL,
  latitude           DECIMAL(10, 8),
  longitude          DECIMAL(10, 8),
  name               VARCHAR(255),
  restaurant_type_id BIGINT,
  city_id            BIGINT,
  PRIMARY KEY (id)
);

CREATE TABLE restaurant_type (
  id   BIGINT NOT NULL,
  type VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE orders (
  id            BIGINT  NOT NULL,
  in_progress   BOOLEAN NOT NULL,
  customer_id   BIGINT,
  motoboy_id    BIGINT,
  restaurant_id VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE order_product_list (
  order_id   BIGINT       NOT NULL,
  product_id VARCHAR(255) NOT NULL
);

ALTER TABLE product
  ADD CONSTRAINT FK_product_city FOREIGN KEY (city_id) REFERENCES city;
ALTER TABLE product
  ADD CONSTRAINT FK_product_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant;
ALTER TABLE restaurant
  ADD CONSTRAINT FK_restaurant_city FOREIGN KEY (city_id) REFERENCES city;
ALTER TABLE restaurant
  ADD CONSTRAINT FK_restaurant_type FOREIGN KEY (restaurant_type_id) REFERENCES restaurant_type;
ALTER TABLE orders
  ADD CONSTRAINT FK_order_customer FOREIGN KEY (customer_id) REFERENCES customer;
ALTER TABLE orders
  ADD CONSTRAINT FK_order_motoboy FOREIGN KEY (motoboy_id) REFERENCES motoboy;
ALTER TABLE orders
  ADD CONSTRAINT FK_order_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant;
ALTER TABLE order_product_list
  ADD CONSTRAINT FK_order_product_list FOREIGN KEY (order_id) REFERENCES orders;
ALTER TABLE order_product_list
  ADD CONSTRAINT FK_product_order_list FOREIGN KEY (product_id) REFERENCES product;