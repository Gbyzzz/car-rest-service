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
VALUES ('1', 1, 2020, 'C-Class'),
       ('2', 1, 2021, 'C-Class'),
       ('3', 1, 2021, 'E-Class'),
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

INSERT INTO cars
VALUES (1, 1, 'Silver', '2765AV'),
       (2, 11, 'Black', '2711AW'),
       (3, 3, 'Green', '2768AV'),
       (4, 8, 'White', '3765AQ');
SELECT setval('cars_id_seq', (SELECT MAX(id) from "cars"));