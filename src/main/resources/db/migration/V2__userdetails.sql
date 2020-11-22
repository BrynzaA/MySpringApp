CREATE TABLE IF NOT EXISTS user_details
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
CREATE TABLE IF NOT EXISTS user_authority
(
    authority_id int8,
    user_id int8,
    FOREIGN KEY (authority_id) REFERENCES authority(id),
    FOREIGN KEY (user_id) REFERENCES user_details(id)
);

INSERT INTO user_details (id, username, password) VALUES (1, 'admin', '$2y$12$GEiD1FfhRc22AqgF8kmJwe51nknKX4B9nTfIytCjJC4Ps9CViLGAy');
INSERT INTO user_details (id, username, password) VALUES (2, 'user', '$2y$12$MMgwp0QJdF9CUtJHT.SaxeBiceYwi9Bt/n4r7azDGMycL78YzsTgS');


INSERT INTO authority(name, id) VALUES ('ROLE_ADMIN', 1);
INSERT INTO authority(name, id) VALUES ('ROLE_USER', 2);

INSERT INTO user_authority(authority_id, user_id) VALUES (1, 1);
INSERT INTO user_authority(authority_id, user_id) VALUES (2, 2);


