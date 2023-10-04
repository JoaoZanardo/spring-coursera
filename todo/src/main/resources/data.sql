CREATE TABLE todo
(
    id        VARCHAR(32) PRIMARY KEY,
    title     VARCHAR(25)  NOT NULL UNIQUE,
    body      VARCHAR(300) NOT NULL,
    createdAt DATE DEFAULT CURRENT_DATE
);

CREATE TABLE users
(
    id          VARCHAR(32) PRIMARY KEY,
    AccountName VARCHAR(10) NOT NULL UNIQUE,
    password    VARCHAR     NOT NULL,
    role        VARCHAR     NOT NULL,
    createdAt   DATE DEFAULT CURRENT_DATE
);

