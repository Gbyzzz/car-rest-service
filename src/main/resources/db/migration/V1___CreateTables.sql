CREATE TYPE car_rest_service_user_role AS ENUM ('ADMIN', 'STAFF', 'CUSTOMER');


CREATE TABLE brand
(
    id      bigserial PRIMARY KEY,
    name    varchar(45) UNIQUE NOT NULL
);

CREATE TABLE model
(
    id              varchar(10) PRIMARY KEY,
    brand_id        bigint REFERENCES brand(id) ON DELETE CASCADE,
    year            integer NOT NULL,
    name    varchar(45) NOT NULL,
    UNIQUE(brand_id, year, name)
);

CREATE TABLE type
(
    id   bigserial PRIMARY KEY,
    name varchar(30) UNIQUE  NOT NULL
);

CREATE TABLE car_model_type
(
    id           bigserial PRIMARY KEY,
    model_id     varchar(10) REFERENCES model(id),
    type_id      bigint REFERENCES type(id),
    UNIQUE (model_id, type_id)
);


CREATE TABLE cars
(
    id                 bigserial PRIMARY KEY,
    car_model_type_id  bigint REFERENCES car_model_type(id),
    car_color          varchar(15),
    car_plate          varchar(10) UNIQUE
);

CREATE TABLE users
(
    user_id bigserial PRIMARY KEY,
    email varchar(256) UNIQUE NOT NULL,
    password varchar(200) NOT NULL,
    first_name varchar(15) DEFAULT NULL,
    last_name varchar(30) DEFAULT NULL,
    role car_rest_service_user_role NOT NULL DEFAULT 'CUSTOMER',
    phone varchar(15) UNIQUE DEFAULT NULL
);