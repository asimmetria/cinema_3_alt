INSERT INTO users(id, email, first_name, last_name, password, enable)
VALUES (100, 'user100@gmail.com', 'Ivan', 'Ivanov', 'password', true),
       (101, 'user101@gmail.com', 'Petr', 'Petrov', 'password', true),
       (102, 'user102@gmail.com', 'Alexander', 'Pushkin', 'password', true);

INSERT INTO media(id, title, html_body, user_id, enable)
VALUES (1, 'First title', 'The big text1', 102, true),
       (2, 'Second title', 'The big text2', 102, true);

INSERT INTO comments(id, message, user_id, media_id, is_moderate, level)
VALUES (1, 'First message', 1, 1, true, 1),
       (2, 'Second message', 2, 1, true, 1),
       (3, 'Third message', 1, 2, true, 1);