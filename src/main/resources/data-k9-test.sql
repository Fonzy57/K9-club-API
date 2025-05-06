INSERT INTO dog (name, birthday, gender, created_at)
VALUES ('Rex', '2020-05-10', 'Male', '2024-04-10 14:30:00'),
       ('Simba', '2018-08-22', 'Female', '2024-04-11 10:00:00'),
       ('Néo', '2025-01-01', NULL, '2024-04-12 09:45:00');
-- exemple avec genre NULL

-- The password is "123456"
INSERT INTO user (firstname, lastname, email, password, user_role, created_at, updated_at)
VALUES
    -- Utilisateurs existants
    ('Stéphane', 'Scheeres', 'super-admin@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'SUPER_ADMIN',
     UTC_TIMESTAMP() - INTERVAL 10 DAY,
     UTC_TIMESTAMP() - INTERVAL 7 DAY),
    ('Victor', 'Monteragioni', 'admin@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'ADMIN',
     UTC_TIMESTAMP() - INTERVAL 9 DAY,
     UTC_TIMESTAMP() - INTERVAL 6 DAY),
    ('Tetiana', 'Lombardi', 'user@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'OWNER',
     UTC_TIMESTAMP() - INTERVAL 8 DAY,
     UTC_TIMESTAMP() - INTERVAL 5 DAY),
    ('Hubert', 'Bonisseur de la Bath', 'coach@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 7 DAY,
     UTC_TIMESTAMP() - INTERVAL 4 DAY),

    -- Nouveaux COACH issus des personnages OSS 117 :
    ('Larmina', 'El-Akmar Betouche', 'larmina.el-akmar-betouche@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 6 DAY,
     UTC_TIMESTAMP() - INTERVAL 3 DAY),
    ('Jack', 'Jefferson', 'jack.jefferson@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 5 DAY,
     UTC_TIMESTAMP() - INTERVAL 2 DAY),
    ('Princess', 'Al-Tarouk', 'princess.al-tarouk@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 4 DAY,
     UTC_TIMESTAMP() - INTERVAL 1 DAY),
    ('Dolorès', 'Koulechov', 'dolores.koulechov@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 3 DAY,
     UTC_TIMESTAMP()),
    ('Heinrich', 'Von-Zimmel', 'von.zimmel@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 2 DAY,
     UTC_TIMESTAMP() + INTERVAL 1 HOUR);



