INSERT INTO cars
VALUES (1, 434, 'Red', '2412WA'),
       (2, 234, 'White', '2481BA'),
       (3, 5424, 'Silver', '2434FD'),
       (4, 7234, 'Black', '7415GF');
SELECT setval('cars_id_seq', (SELECT MAX(id) from "cars"));