INSERT INTO users(id, email, first_name, password, enable)
VALUES (100, 'adm@gmail.ru', 'Имя1', '$2a$11$Ig/7qJn7s0gllAzuvFU9vuF.FBUJKiJtzJc4xJmlnUcXklbo1QVO.', true);

INSERT INTO roles(id, name)
VALUES (100, 'ADMIN');

INSERT INTO users_role(user_id, role_id)
VALUES (100, 100);


INSERT INTO movies(id, date_release, description, mpaa, name, rars, time, type, original_name)
VALUES (21, '2000-12-22', 'The so scary movie', 'PARENTS_STRONGLY_CAUTIONED', 'Saw', 'EIGHTEEN_PLUS', 95, 'MOVIE', 'оригинальное название'),
       (25, '1995-04-22', 'Action Movie', 'PARENTS_STRONGLY_CAUTIONED', 'Terminator', 'EIGHTEEN_PLUS', 123, 'MOVIE', 'оригинальное название');
INSERT INTO countries(id, name)
VALUES (33, 'Russia'),
       (27, 'USA'),
       (91, 'China');
INSERT INTO movie_countries(movie_id, country_id)
VALUES (21, 27),
       (21, 91);
