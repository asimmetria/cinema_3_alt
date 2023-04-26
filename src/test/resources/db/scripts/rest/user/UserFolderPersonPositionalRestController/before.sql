-- Добавление тестовых данных в таблицу users
INSERT INTO users(id, email, first_name, password, enable)
VALUES (100, 'email1@mail.ru', 'Имя1', 'password', true),
       (101, 'email2@mail.ru', 'Имя2', 'password', true);

-- Добавление тестовых данных в таблицу folders
INSERT INTO folders(id, privacy, name, description, user_id, type_folder, type)
VALUES (100, 'PUBLIC', 'Новая папка1', 'описание', 100, 'folders_persons', 'CUSTOM'),
       (101, 'PUBLIC', 'Новая папка2', 'описание', 100, 'folders_persons', 'VIEWED_MOVIES'),
       (102, 'PUBLIC', 'Новая папка3', 'описание', 101, 'folders_persons', 'CUSTOM'),
       (103, 'PUBLIC', 'Новая папка4', 'описание', 100, 'folders_persons', 'FAVOURITES');

-- Добавление тестовых данных в таблицу persons
INSERT INTO persons (id, original_first_name, first_name, last_name, height, date_birth, place_of_birth)
VALUES (100, 'Firstname1', 'John', 'Doe', 175.5, '1990-05-15', 'New York'),
       (101, 'Firstname2', 'Jane', 'Smith', 160.0, '1995-02-28', 'Los Angeles'),
       (102, 'Firstname3', 'Michael', 'Johnson', 185.2, '1988-11-10', 'Chicago'),
       (103, 'Firstname4', 'Sarah', 'Brown', 170.8, '1993-07-20', 'Houston'),
       (104, 'Firstname5', 'Ashley', 'McNiel', 158.8, '2000-09-12', 'Washington'),
       (105, 'Firstname6', 'Peter', 'Taylor', 188.0, '2002-01-05', 'Austin');


-- Добавление тестовых данных в таблицу folders_movies_positional
-- Добавление записи 1
INSERT INTO folders_persons_positional (id, person, folder, positional)
VALUES (
            100,
            (SELECT id FROM persons WHERE first_name = 'John' AND last_name = 'Doe'), -- Здесь указываем id нужной персоны (100)
            (SELECT id FROM folders WHERE type_folder = 'folders_persons' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки (100)
            1 -- Здесь указываем позицию персоны в папке
       );

-- Добавление записи 2
INSERT INTO folders_persons_positional (id, person, folder, positional)
VALUES (
            101,
            (SELECT id FROM persons WHERE first_name = 'Jane' AND last_name = 'Smith'), -- Здесь указываем id нужной персоны (101)
            (SELECT id FROM folders WHERE type_folder = 'folders_persons' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки (100)
            2 -- Здесь указываем позицию персоны в папке
       );

-- Добавление записи 3
INSERT INTO folders_persons_positional (id, person, folder, positional)
VALUES (
           102,
           (SELECT id FROM persons WHERE first_name = 'Michael' AND last_name = 'Johnson'), -- Здесь указываем id нужной персоны (102)
           (SELECT id FROM folders WHERE type_folder = 'folders_persons' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки (100)
           3 -- Здесь указываем позицию персоны в папке
       );

-- Добавление записи 4
INSERT INTO folders_persons_positional (id, person, folder, positional)
VALUES (
           103,
           (SELECT id FROM persons WHERE first_name = 'Sarah' AND last_name = 'Brown'), -- Здесь указываем id нужной персоны (103)
           (SELECT id FROM folders WHERE type_folder = 'folders_persons' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки (100)
           4 -- Здесь указываем позицию персоны в папке
       );

-- Добавление записи 5
INSERT INTO folders_persons_positional (id, person, folder, positional)
VALUES (
           104,
           (SELECT id FROM persons WHERE first_name = 'Ashley' AND last_name = 'McNiel'), -- Здесь указываем id нужной персоны (104)
           (SELECT id FROM folders WHERE type_folder = 'folders_persons' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки (100)
           5 -- Здесь указываем позицию персоны в папке
       );