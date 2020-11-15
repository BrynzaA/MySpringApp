CREATE SEQUENCE IF NOT EXISTS hibernate_sequence start 1000 increment 1;

CREATE TABLE IF NOT EXISTS Employee
(
    id               serial PRIMARY KEY,
    name             varchar(255) NOT NULL,
    surName          varchar(255) NOT NULL,
    dateOfBirth      date         NOT NULL,
    dateOfEmployment date         NOT NULL
);

CREATE TABLE IF NOT EXISTS Staff
(
    id                  serial    PRIMARY KEY,
    employee_id         int8,
    position            varchar(255) NOT NULL,
    salary              float        NOT NULL
);
