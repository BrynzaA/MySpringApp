CREATE SEQUENCE IF NOT EXISTS hibernate_sequence start 1000 increment 1;

CREATE TABLE IF NOT EXISTS Employee
(
    id               serial PRIMARY KEY,
    name             varchar(255) NOT NULL,
    surname          varchar(255) NOT NULL,
    date_of_birth      date         NOT NULL,
    date_of_employment date         NOT NULL
);

CREATE TABLE IF NOT EXISTS Staff
(
    id                  serial    PRIMARY KEY,
    employee_id         int8,
    position            varchar(255) NOT NULL,
    salary              float        NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES Employee (id)
);
