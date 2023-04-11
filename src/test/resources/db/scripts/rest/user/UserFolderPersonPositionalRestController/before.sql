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
INSERT INTO persons (id, first_name, last_name, height, date_birth, place_of_birth)
VALUES (100, 'John', 'Doe', 175.5, '1990-05-15', 'New York'),
       (101, 'Jane', 'Smith', 160.0, '1995-02-28', 'Los Angeles'),
       (102, 'Michael', 'Johnson', 185.2, '1988-11-10', 'Chicago'),
       (103, 'Sarah', 'Brown', 170.8, '1993-07-20', 'Houston'),
       (104, 'David', 'Taylor', 195.1, '1985-09-05', 'Miami'),
       (105, 'Emily', 'Wilson', 155.7, '2000-04-18', 'San Francisco');

-- Добавление тестовых данных в таблицу folders_movies_positional
-- Добавление записи 1
INSERT INTO folders_persons_positional (id, person, folder, positional)
VALUES (
            1,
            (SELECT id FROM persons WHERE first_name = 'John' AND last_name = 'Doe'), -- Здесь указываем id нужной персоны
            (SELECT id FROM folders WHERE type_folder = 'folders_persons' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки
            1 -- Здесь указываем позицию персоны в папке
       );

-- Добавление записи 2
INSERT INTO folders_persons_positional (id, person, folder, positional)
VALUES (
            2,
            (SELECT id FROM persons WHERE first_name = 'Jane' AND last_name = 'Smith'), -- Здесь указываем id нужной персоны
            (SELECT id FROM folders WHERE type_folder = 'folders_persons' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки
            2 -- Здесь указываем позицию персоны в папке
       );

-- Добавление записи 2
INSERT INTO folders_persons_positional (id, person, folder, positional)
VALUES (
           3,
           (SELECT id FROM persons WHERE first_name = 'Michael' AND last_name = 'Johnson'), -- Здесь указываем id нужной персоны
           (SELECT id FROM folders WHERE type_folder = 'folders_persons' AND name = 'Новая папка1'), -- Здесь указываем id нужной папки
           3 -- Здесь указываем позицию персоны в папке
       );