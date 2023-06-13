INSERT INTO users
VALUES (1, 'admin@car.com', '$2a$10$kTtZAqCVvwUk0V3q3igTj.isbnf9xhGNIY/Tnn6TPJZYmlSc4lo.m', 'John', 'Smith', 'ADMIN', '138765487'),
       (2, 'teacher1@car.com', '$2a$10$kTtZAqCVvwUk0V3q3igTj.isbnf9xhGNIY/Tnn6TPJZYmlSc4lo.m', 'Tom', 'Green', 'STAFF', '135481314'),
       (3, 'teacher2@car.com', '$2a$10$kTtZAqCVvwUk0V3q3igTj.isbnf9xhGNIY/Tnn6TPJZYmlSc4lo.m', 'Anthony', 'Newman', 'CUSTOMER', '846321871'),
       (4, 'student1@car.com', '$2a$10$kTtZAqCVvwUk0V3q3igTj.isbnf9xhGNIY/Tnn6TPJZYmlSc4lo.m', 'Kimberly', 'Foster', 'CUSTOMER', '9318431812');
SELECT setval('users_user_id_seq', (SELECT MAX(user_id) from "users"));