CREATE TABLE products
(
    id    SERIAL,
    title VARCHAR(100),
    price NUMERIC(6, 2)
);

CREATE TABLE users
(
    id       SERIAL,
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(80)  NOT NULL,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(50)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   serial,
    name VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
        REFERENCES roles (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO products (title, price)
VALUES ('Product #1', 10),
       ('Product #2', 20),
       ('Product #3', 30),
       ('Product #4', 40),
       ('Product #5', 50),
       ('Product #6', 60),
       ('Product #7', 70),
       ('Product #8', 80),
       ('Milk', 90),
       ('Milk #2', 100);

INSERT INTO users (username, password, name, email)
VALUES ('admin', '$2a$10$CfaEU2c9rCGP0KoL.vWwdOyh6fmbRqCuEdqyBSp.emre90RRZKwaO', 'Aleksey Buyvalenko',
        'AlekseyBuyvalenko@gmail.com');

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2);
