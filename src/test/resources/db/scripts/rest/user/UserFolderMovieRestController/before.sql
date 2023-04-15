INSERT INTO users(id, email, first_name, password, enable)
VALUES (100, 'email1@mail.ru', 'Имя1', 'password', true),
       (101, 'email2@mail.ru', 'Имя2', 'password', true);

INSERT INTO folders(id, privacy, name, description, user_id, type_folder, type)
VALUES (100, 'PUBLIC', 'Новая папка1', 'описание', 100, 'MOVIE_FOLDER', 'CUSTOM'),
       (101, 'PUBLIC', 'Новая папка2', 'описание', 100, 'MOVIE_FOLDER', 'VIEWED_MOVIES'),
       (102, 'PUBLIC', 'Новая папка3', 'описание', 101, 'MOVIE_FOLDER', 'CUSTOM'),
       (103, 'PUBLIC', 'Новая папка4', 'описание', 100, 'MOVIE_FOLDER', 'FAVORITE_MOVIES')