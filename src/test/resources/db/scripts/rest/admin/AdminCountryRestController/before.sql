INSERT INTO movies(id, date_release, description, mpaa, name, rars, time, type)
VALUES (21, '2000-12-22', 'The so scary movie', 'PARENTS_STRONGLY_CAUTIONED', 'Saw', 'EIGHTEEN_PLUS', 95, 'MOVIE'),
       (25, '1995-04-22', 'Action Movie', 'PARENTS_STRONGLY_CAUTIONED', 'Terminator', 'EIGHTEEN_PLUS', 123, 'MOVIE');
INSERT INTO countries(id, name)
VALUES (33, 'Russia'),
       (27, 'USA'),
       (91, 'China');
INSERT INTO movie_countries(movieId, countryId)
VALUES (21, 27),
       (21, 91);
