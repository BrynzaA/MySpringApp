CREATE TABLE IF NOT EXISTS users
(

    id serial PRIMARY KEY,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS authority
(
    name varchar(255) NOT NULL,
    id serial PRIMARY KEY
);
CREATE TABLE IF NOT EXISTS users_authority
(
    authority_id int8,
    users_id int8,
    FOREIGN KEY (authority_id) REFERENCES authority(id),
    FOREIGN KEY (users_id) REFERENCES users(id)
);
CREATE TABLE IF NOT EXISTS permission
(
    name varchar(255) NOT NULL,
    id serial PRIMARY KEY
);



CREATE TABLE IF NOT EXISTS authority_permission
(
    authority_id int8,
    permission_id int8,
    FOREIGN KEY (authority_id) REFERENCES authority(id),
    FOREIGN KEY (permission_id) REFERENCES permission(id)
);

INSERT INTO users (id, username, password) VALUES (1, 'admin', '$2y$12$GEiD1FfhRc22AqgF8kmJwe51nknKX4B9nTfIytCjJC4Ps9CViLGAy');
INSERT INTO users (id, username, password) VALUES (2, 'user', '$2y$12$MMgwp0QJdF9CUtJHT.SaxeBiceYwi9Bt/n4r7azDGMycL78YzsTgS');


INSERT INTO authority(name, id) VALUES ('ROLE_ADMIN', 1);
INSERT INTO authority(name, id) VALUES ('ROLE_USER', 2);

INSERT INTO users_authority(authority_id, users_id) VALUES (1, 1);
INSERT INTO users_authority(authority_id, users_id) VALUES (2, 2);

INSERT INTO permission (name, id) VALUES ('EMPLOYEE_EDIT', 1);
INSERT INTO permission (name, id) VALUES ('EMPLOYEE_READ', 2);
INSERT INTO permission (name, id) VALUES ('STAFF_EDIT', 3);
INSERT INTO permission (name, id) VALUES ('STAFF_READ', 4);

INSERT INTO authority_permission (authority_id, permission_id) VALUES (1, 1);
INSERT INTO authority_permission (authority_id, permission_id) VALUES (1, 2);
INSERT INTO authority_permission (authority_id, permission_id) VALUES (1, 3);
INSERT INTO authority_permission (authority_id, permission_id) VALUES (1, 4);
INSERT INTO authority_permission (authority_id, permission_id) VALUES (2, 2);
INSERT INTO authority_permission (authority_id, permission_id) VALUES (2, 4);


