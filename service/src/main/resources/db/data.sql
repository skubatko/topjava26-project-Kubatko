INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@mail.ru', '{noop}guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3);

INSERT INTO restaurants (name)
VALUES ('Sadko'),
       ('Pizza Hut'),
       ('Fridays'),
       ('Europe');

INSERT INTO dishes (name)
VALUES ('Salad'),
       ('Soup'),
       ('Meat'),
       ('Drink');

INSERT INTO menu_items (day, price, dish_id, restaurant_id)
VALUES ('2022-5-15', 25000, 1, 1),
       ('2022-5-15', 45000, 2, 1),
       ('2022-5-15', 15000, 4, 1),
       ('2022-5-15', 30000, 1, 2),
       ('2022-5-15', 85000, 3, 2),
       ('2022-5-15', 40000, 4, 2),
       ('2022-5-15', 135000, 3, 3),
       ('2022-5-15', 50000, 4, 3),
       ('2022-5-15', 125000, 1, 4),
       ('2022-5-15', 45000, 4, 4),
       ('2022-5-16', 23000, 1, 1),
       ('2022-5-16', 47000, 2, 1),
       ('2022-5-16', 10000, 4, 1),
       ('2022-5-16', 35000, 1, 2),
       ('2022-5-16', 75000, 3, 2),
       ('2022-5-16', 42000, 4, 2),
       ('2022-5-16', 125000, 3, 3),
       ('2022-5-16', 45000, 4, 3),
       ('2022-5-16', 115000, 1, 4),
       ('2022-5-16', 40000, 4, 4);

INSERT INTO votes (day, user_id, restaurant_id)
VALUES ('2022-5-15', 1, 1),
       ('2022-5-15', 2, 3),
       ('2022-5-15', 3, 3),
       ('2022-5-16', 1, 2),
       ('2022-5-16', 2, 4);
