CREATE SEQUENCE city_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE customer_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE motoboy_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE restaurant_type_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE city (
  id   BIGINT NOT NULL,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE customer (
  id        BIGINT NOT NULL,
  latitude  DECIMAL(2, 8),
  longitude DECIMAL(2, 8),
  PRIMARY KEY (id)
);

CREATE TABLE motoboy (
  id        BIGINT NOT NULL,
  latitude  DECIMAL(2, 8),
  longitude DECIMAL(2, 8),
  PRIMARY KEY (id)
);

CREATE TABLE product (
  id            UUID NOT NULL,
  category      VARCHAR(255),
  description   VARCHAR(255),
  unit_price    DECIMAL(2, 8),
  city_id       BIGINT,
  restaurant_id UUID,
  PRIMARY KEY (id)
);

CREATE TABLE restaurant (
  id        UUID NOT NULL,
  latitude  DECIMAL(2, 8),
  longitude DECIMAL(2, 8),
  name      VARCHAR(255),
  type      VARCHAR(255),
  city_id   BIGINT,
  PRIMARY KEY (id)
);

CREATE TABLE restaurant_type (
  id   BIGINT NOT NULL,
  type VARCHAR(255),
  PRIMARY KEY (id)
);

ALTER TABLE product
  ADD CONSTRAINT FK_product_city FOREIGN KEY (city_id) REFERENCES city;
ALTER TABLE product
  ADD CONSTRAINT FK_product_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant;
ALTER TABLE restaurant
  ADD CONSTRAINT FK_restaurant_city FOREIGN KEY (city_id) REFERENCES city;