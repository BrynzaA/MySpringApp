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