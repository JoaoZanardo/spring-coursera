CREATE TABLE todo
(
    id    VARCHAR(36) PRIMARY KEY,
    title VARCHAR(25)  NOT NULL UNIQUE,
    body  VARCHAR(300) NOT NULL
);

CREATE TABLE users
(
    id       VARCHAR(36) PRIMARY KEY,
    account  VARCHAR(10) NOT NULL UNIQUE,
    password VARCHAR     NOT NULL,
    role     VARCHAR     NOT NULL
);