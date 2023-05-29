INSERT INTO users(id, email, first_name, password, enable)
VALUES (100, 'adm@gmail.ru', 'Имя1', '$2a$10$8pMS5fgwc0xhsI.zCFkqMOFYOt1BVIPfAtsiWZFykWxU8mKvxNLfe', true);

INSERT INTO production_studios(id, date_foundation, name, description)
VALUES (15, '03-04-2003', 'First', 'description115'),
       (16, '06-04-2001', 'Second', 'description216');

INSERT INTO users_role(user_id, role_id)
VALUES (100, 1);
