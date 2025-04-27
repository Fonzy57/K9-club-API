INSERT INTO dog (name, birthday, gender, created_at)
VALUES ('Rex', '2020-05-10', 'Male', '2024-04-10 14:30:00'),
       ('Simba', '2018-08-22', 'Female', '2024-04-11 10:00:00'),
       ('Néo', '2025-01-01', NULL, '2024-04-12 09:45:00'); -- exemple avec genre NULL

INSERT INTO user (firstname, lastname, email, password, user_role)
VALUES ('Steph', 'Admin', 'admin@k9club.fr', '$2a$10$wH77g1n1hG2JwqVnCmAoqO2uk9NVRJID8ISlYogmTOXGRc3Z82gsa', 'ADMIN'),
       ('Victor', 'Coach', 'coach@k9club.fr', '$2a$10$wH77g1n1hG2JwqVnCmAoqO2uk9NVRJID8ISlYogmTOXGRc3Z82gsa', 'COACH'),
       ('Tetiana', 'User', 'user@k9club.fr', '$2a$10$wH77g1n1hG2JwqVnCmAoqO2uk9NVRJID8ISlYogmTOXGRc3Z82gsa', 'USER');
