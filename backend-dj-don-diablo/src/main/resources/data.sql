INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_MODERATOR');
INSERT INTO role(name) VALUES('ROLE_ADMIN');


INSERT INTO app_user (first_name, last_name, username, country, email, password)
    VALUES ('Novi-admin', 'Novi-admin', 'Novi-admin', 'Nederland', 'admin@novi.nl','$2a$10$.HMF9Rs0uH8SYFydNwjHD.uRp2LJrXQagdBf2pb9crnkkSjsrzbvi');

INSERT INTO app_user (first_name, last_name, username, country, email, password)
    VALUES ('Novi-user', 'Novi-user', 'Novi-user', 'Nederland', 'user@novi.nl','$2a$10$I9px261JpIzSPfdMloG6RulsRki4XOGFa9y3KhYwz1zNCqhlSpm1i');

INSERT INTO user_role (user_id , role_id) VALUES (1,3);
INSERT INTO user_role (user_id , role_id) VALUES (2,1);
