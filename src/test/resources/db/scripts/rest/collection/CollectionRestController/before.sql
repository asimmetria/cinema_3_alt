
INSERT INTO collection_category(id, name)
VALUES (22, 'category'),
       (37, 'category');

INSERT INTO collections(id, description, name, category_id, collection_url, enable)
VALUES (1, 123174, 'Movies for dinner', 22, '/collection/1', 10),
       (2, 123636, 'Movies for children', 37, '/collection/2', 15);

INSERT INTO users(id, first_name, email, password)
VALUES (91, 'Oleg', 'email1@gmail.com', 'password'),
       (44, 'Stas', 'email2@gmail.com', 'password'),
       (75, 'Denis', 'email3@gmail.com', 'password');

INSERT INTO movies(id, type, name, time)
VALUES (10, 'MOVIE', 'movie', 100),
       (12, 'MOVIE', 'movie', 120),
       (15, 'MOVIE', 'movie', 122);

INSERT INTO folders(id, privacy, name, description, user_id, type_folder, type)
VALUES (100, 'PUBLIC', 'Новая папка1', 'описание', 91, 'folders_movies', 'CUSTOM'),
       (101, 'PUBLIC', 'Новая папка2', 'описание', 44, 'folders_movies', 'VIEWED_MOVIES');


INSERT INTO folders_movies_positional(id, positional, folder, movie)
VALUES (1, 1, 100, 15),
       (2, 2, 101, 10),
       (3, 3, 101, 12);

