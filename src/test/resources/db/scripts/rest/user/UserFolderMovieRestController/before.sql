INSERT INTO users(id, email, first_name, password, enable)
VALUES (100, 'email1@mail.ru', 'Имя1', 'password', true),
       (101, 'email2@mail.ru', 'Имя2', 'password', true);

INSERT INTO folders(id, user_id, privacy, name, description, type_folder, type)
VALUES (100, 100, 'PUBLIC','Новая папка1', 'описание',  'folders_movies', 'CUSTOM'),
       (101, 100, 'PUBLIC','Новая папка2', 'описание',  'folders_movies', 'VIEWED_MOVIES'),
       (102, 101, 'PUBLIC','Новая папка3', 'описание',  'folders_movies', 'CUSTOM'),
       (103, 100, 'PUBLIC','Новая папка4', 'описание',  'folders_movies', 'FAVORITE_MOVIES')

