-- Добавление тестовых данных в таблицу users
INSERT INTO users(id, email, first_name, password, enable)
VALUES (100, 'email1@mail.ru', 'Имя1', 'password', true),
       (101, 'email2@mail.ru', 'Имя2', 'password', true);

-- Добавление тестовых данных в таблицу folders
INSERT INTO folders (id, privacy, name, description, user_id, type_folder, type)
VALUES (100, 'PUBLIC', 'Новая папка1', 'описание', 100, 'folders_movies', 'CUSTOM'),
       (101, 'PUBLIC', 'Новая папка2', 'описание', 100, 'folders_movies', 'VIEWED_MOVIES'),
       (102, 'PUBLIC', 'Новая папка3', 'описание', 101, 'folders_movies', 'CUSTOM'),
       (103, 'PUBLIC', 'Новая папка4', 'описание', 100, 'folders_movies', 'FAVOURITES');

-- Добавление тестовых данных в таблицу movies
INSERT INTO movies (id, name, countries, date_release, rars, mpaa, time, description, type)
VALUES (100, 'Тест 1', 'Страна 1, Страна 2', '2022-01-01', 'SIXTEEN_PLUS', 'PARENTS_STRONGLY_CAUTIONED', 120, 'Описание 1', 'MOVIE'),
       (101, 'Тест 2', 'Страна 3, Страна 4', '2022-02-02', 'TWELVE_PLUS', 'PARENTAL_GUIDANCE_SUGGESTED', 90, 'Описание 2', 'SERIAL'),
       (102, 'Тест 3', 'Страна 5, Страна 6', '2022-03-03', 'SIX_PLUS', 'GENERAL_AUDIENCES', 150, 'Описание 3', 'CARTOON'),
       (103, 'Тест 4', 'Страна 7, Страна 8', '2022-04-04', 'TWELVE_PLUS', 'GENERAL_AUDIENCES', 160, 'Описание 4', 'MOVIE');

-- Добавление тестовых данных в таблицу folders_movies_positional
-- Добавление записи 1
INSERT INTO folders_movies_positional (id, movie, folder, positional)
VALUES (
            100,
            (SELECT id FROM movies WHERE name = 'Тест 1'), -- Здесь указываем id нужного фильма
            (SELECT id FROM folders WHERE type_folder = 'folders_movies' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки
            1 -- Здесь указываем позицию фильма в папке
       );

-- Добавление записи 2
INSERT INTO folders_movies_positional (id, movie, folder, positional)
VALUES (
            101,
            (SELECT id FROM movies WHERE name = 'Тест 2'), -- Здесь указываем id нужного фильма (101)
            (SELECT id FROM folders WHERE type_folder = 'folders_movies' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки (100)
            2 -- Здесь указываем позицию фильма в папке
       );

-- Добавление записи 3
INSERT INTO folders_movies_positional (id, movie, folder, positional)
VALUES (
           102,
           (SELECT id FROM movies WHERE name = 'Тест 3'), -- Здесь указываем id нужного фильма (102)
           (SELECT id FROM folders WHERE type_folder = 'folders_movies' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки (100)
           3 -- Здесь указываем позицию фильма в папке
       );