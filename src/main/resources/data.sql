insert into role (role, role_id) values ("ADMIN", 1);
insert into role (role, role_id) values ("USER", 2);
insert into user (user_id, username, password) values (1, "admin", "$2a$11$Rj5m5B9oEsrEUYILVpglyulqwc8CPIwbKzXHgTd9mMP5o6OsDsQPy");
insert into user_roles (user_user_id, roles_role_id) values (1, 1);