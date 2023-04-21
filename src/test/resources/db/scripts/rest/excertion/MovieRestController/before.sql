INSERT INTO movies(id, date_release, description, mpaa, name, original_name, rars, time, type)
VALUES (1, '03/04/2023', 'First movie', 'GENERAL_AUDIENCES', 'Cool', 'Первый фильм', 'ZERO_PLUS', 100, 'MOVIE'),
       (2, '06/04/2023', 'Second movie', 'GENERAL_AUDIENCES', 'Not cool', 'Второй фильм', 'ZERO_PLUS', 200, 'MOVIE');

INSERT INTO excertion(id, description, movie_id)
VALUES (1, 'The first movie description', 1),
       (2, 'The second movie description', 1),
       (3, 'The third movie description', 2);