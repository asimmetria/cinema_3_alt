INSERT INTO persons(id, date_birth, first_name, original_first_name, height, last_name, original_last_name,
                    place_of_birth)
VALUES (1, '01/01/2000', 'Ivan', 'Иван', 180, 'Ivanov', 'Иванов', 'Moscow'),
       (2, '03/03/2003', 'Vlada', 'Влада', 380, 'Petrova', 'Петрова', 'Murmansk');

INSERT INTO excertion(id, description, person_id)
VALUES (1, 'The first description', 1),
       (2, 'The second description', 1),
       (3, 'The third description', 2);