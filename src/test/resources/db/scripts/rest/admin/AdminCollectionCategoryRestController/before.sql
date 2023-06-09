INSERT INTO users(id, email, first_name, password, enable)
VALUES (100, 'adm@gmail.ru', 'Имя1', '$2a$11$Ig/7qJn7s0gllAzuvFU9vuF.FBUJKiJtzJc4xJmlnUcXklbo1QVO.', true);

INSERT INTO roles(id, name)
VALUES (100, 'ADMIN');

INSERT INTO users_role(user_id, role_id)
VALUES (100, 100);


INSERT INTO collection_category(id, name)
VALUES (1, 'Category 1'),
       (2, 'Category 2'),
       (3, 'Category 3');