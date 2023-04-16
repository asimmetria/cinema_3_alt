INSERT INTO movies (id, countries, date_release, description, mpaa, name, original_name, rars, time, type)
VALUES (1, 'RUSSIA', '03/04/2023', 'First description', 'GENERAL_AUDIENCES', 'Cool', 'Крутой', 'ZERO_PLUS', 100,
        'MOVIE'),
       (2, 'POLAND', '06/04/2023', 'Second description', 'GENERAL_AUDIENCES', 'Not cool', 'Не крутой', 'ZERO_PLUS', 200,
        'MOVIE');

INSERT INTO genres (id, name)
VALUES (1, 'Drama'),
       (2, 'Comedy');

INSERT INTO movie_genre (movie_id, genre_id)
VALUES (1, 1),
       (2, 1),
       (1, 2);

INSERT INTO score (id, score, movie_id, user_id)
VALUES (1, 100, 1, 1),
       (2, 200, 2, 2),
       (3, 200, 1, 2);

INSERT INTO persons (id, date_birth, first_name, height, last_name, original_first_name, original_last_name,
                     place_of_birth)
VALUES (1, '01/01/2000', 'Ivan', 180, 'Ivanov', 'IIII', 'IIIIII', 'Moscow'),
       (2, '02/02/2002', 'Petr', 280, 'Petrov', 'VVV', 'VVVVVV', 'SPB'),
       (3, '03/03/2003', 'Vlada', 380, 'Sidorova', 'XXX', 'XXXXX', 'Murmansk');

INSERT INTO professions (id, name)
VALUES (1, 'Actor'),
       (2, 'Director');

INSERT INTO character (id, age, description, name, type_character, movie_id, person_id)
VALUES (1, 3, 'Character description', 'Character name', 'MAIN_CHARACTER', 1, 1),
       (2, 10, 'Character description second', 'Character name second', 'MAIN_CHARACTER', 1, 2),
       (3, 30, 'Character description third', 'Character name third', 'MAIN_CHARACTER', 1, 3),
       (4, 40, 'Character description second movie', 'Character name second movie', 'MAIN_CHARACTER', 2, 3);

INSERT INTO casts (id, character_id, movie_id, person_id, profession_id)
VALUES (1, 1, 1, 1, 1),
       (2, 2, 1, 2, 2),
       (3, 3, 1, 3, 1),
       (4, 4, 2, 3, 1),
       (5, 1, 1, 1, 2);