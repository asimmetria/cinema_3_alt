-- Добавление тестовых данных в таблицу movies
INSERT INTO movies (id, name, countries, date_release, rars, mpaa, time, description, type)
VALUES (100, 'Фильм 1', 'Страна 1, Страна 2', '2022-01-01', 'SIXTEEN_PLUS', 'PARENTS_STRONGLY_CAUTIONED', 120, 'Описание фильма 1', 'MOVIE'),
       (101, 'Фильм 2', 'Страна 3, Страна 4', '2022-02-02', 'TWELVE_PLUS', 'PARENTAL_GUIDANCE_SUGGESTED', 150, 'Описание фильма 2', 'SERIAL'),
       (102, 'Фильм 3', 'Страна 5, Страна 6', '2022-03-03', 'ZERO_PLUS', 'GENERAL_AUDIENCES', 180, 'Описание фильма 3', 'CARTOON'),
       (103, 'Фильм 4', 'Страна 7, Страна 8', '2022-04-04', 'EIGHTEEN_PLUS', 'NO_ONE_SEVENTEEN_AND_UNDER_ADMITTED', 160, 'Описание фильма 4', 'MOVIE');

-- Добавление тестовых данных в таблицу users
INSERT INTO users (id, email, first_name, last_name, password, birthday, enable)
VALUES (100, 'user1@example.com', 'Имя1', 'Фамилия1', 'Пароль1', '1990-01-01', true),
       (101, 'user2@example.com', 'Имя2', 'Фамилия2', 'Пароль2', '1995-02-02', true),
       (102, 'user3@example.com', 'Имя3', 'Фамилия3', 'Пароль3', '2000-03-03', true);

-- Добавление тестовых данных в таблицу score
-- Добавление записи 1
INSERT INTO score (id, movie_id, user_id, score)
VALUES (
        100,
        (SELECT id FROM movies WHERE id = 100),
        (SELECT id FROM users WHERE id = 100),
        5
       );

-- Добавление записи 2
INSERT INTO score (id, movie_id, user_id, score)
VALUES (
        101,
        (SELECT id FROM movies WHERE id = 100),
        (SELECT id FROM users WHERE id = 101),
        4
       );

-- Добавление записи 3
INSERT INTO score (id, movie_id, user_id, score)
VALUES (
        102,
        (SELECT id FROM movies WHERE id = 101),
        (SELECT id FROM users WHERE id = 102),
        3
       );
