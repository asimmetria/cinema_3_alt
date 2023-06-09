INSERT INTO users(id, email, first_name, password, enable)
VALUES (100, 'adm@gmail.ru', 'Имя1', '$2a$11$Ig/7qJn7s0gllAzuvFU9vuF.FBUJKiJtzJc4xJmlnUcXklbo1QVO.', true);

INSERT INTO roles(id, name)
VALUES (100, 'ADMIN');

INSERT INTO users_role(user_id, role_id)
VALUES (100, 100);

INSERT INTO production_studios(id, date_foundation, name, description)
VALUES (15, '03-04-2003', 'First', 'description115'),
       (16, '06-04-2001', 'Second', 'description216');
