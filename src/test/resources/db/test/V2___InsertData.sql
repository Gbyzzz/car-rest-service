INSERT INTO users
VALUES (1, 'admin@car.com', '1111', 'John', 'Smith', 'ADMIN', '138765487'),
       (2, 'staff@car.com', '1111', 'Tom', 'Green', 'STAFF', '135481314'),
       (3, 'antonynewman@gmail.com', '1111', 'Anthony', 'Newman', 'CUSTOMER', '846321871');
SELECT setval('users_user_id_seq', (SELECT MAX(user_id) from "users"));

INSERT INTO brand
VALUES (1, 'Mercedes'),
       (2, 'BMW'),
       (3, 'Audi');
SELECT setval('brand_id_seq', (SELECT MAX(id) from "brand"));

INSERT INTO type
VALUES (1, 'Sedan'),
       (2, 'Hatchback'),
       (3, 'Coupe');
SELECT setval('type_id_seq', (SELECT MAX(id) from "type"));

INSERT INTO model
VALUES ('1', 1, 2020, 'C-class'),
       ('2', 1, 2021, 'C-class'),
       ('3', 1, 2021, 'E-class'),
       ('4', 2, 2020, '420d'),
       ('5', 2, 2022, '530d'),
       ('6', 2, 2021, '420d'),
       ('7', 3, 2019, 'A4'),
       ('8', 3, 2018, 'A6'),
       ('9', 3, 2019, 'A6');

INSERT INTO car_model_type
VALUES (1, '1', 1),
       (2, '1', 3),
       (3, '2', 1),
       (4, '3', 1),
       (5, '3', 3),
       (6, '4', 1),
       (7, '4', 2),
       (8, '5', 1),
       (9, '6', 1),
       (10, '6', 3),
       (11, '7', 1),
       (12, '7', 3),
       (13, '8', 1),
       (14, '9', 1);
SELECT setval('car_model_type_id_seq', (SELECT MAX(id) from "car_model_type"));