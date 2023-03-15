CREATE TABLE student (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    created_at datetime default now(),
    updated_at datetime default now(),
    balance double NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);
