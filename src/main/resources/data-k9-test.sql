-- Purge complète pour un environnement de développement/test
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE course_registration;
TRUNCATE TABLE course;
TRUNCATE TABLE dog;
TRUNCATE TABLE user;
TRUNCATE TABLE course_type;
TRUNCATE TABLE age_range;
TRUNCATE TABLE breed;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Insertion des races (breed) (10 exemples)
INSERT INTO breed (name, created_at, updated_at)
VALUES ('Labrador Retriever', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger Allemand', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Golden Retriever', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bouledogue Français', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Beagle', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Caniche', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Yorkshire Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Boxer', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Teckel', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Chihuahua', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Cavalier King Charles', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Rottweiler', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger Australien', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Shih Tzu', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Cocker Anglais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Schnauzer nain', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bouledogue Anglais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Epagneul Breton', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bichon Maltais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Staffordshire Bull Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bichon Frisé', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Shetland', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Border Collie', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Dogue Allemand', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Cane Corso', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bouvier Bernois', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Dobermann', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Husky Sibérien', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Akita Inu', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Spitz Nain (Loulou de Poméranie)', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('West Highland White Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Dalmatien', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Jack Russell Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Carlin', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Chow-Chow', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Coton de Tuléar', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Setter Anglais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Lévrier Whippet', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Pékinois', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Shar-Peï', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Dogue de Bordeaux', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Malinois', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Springer Spaniel Anglais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Fox Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger Belge Tervueren', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Léonberger', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Pug', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('American Staffordshire Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger Blanc Suisse', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Caniche Toy', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Chien Chinois à Crête', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Scottish Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bouledogue Boston', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger Picard', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Cairn Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bull Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Papillon', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Schnauzer Standard', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Terre-Neuve', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger de Beauce (Beauceron)', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Mastiff Anglais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Pinscher Nain', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Chien de Saint-Hubert', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Colley', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Irish Setter', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Petit Brabançon', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Labradoodle', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Samoyède', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bracco Italien', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger de Shetland', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Petit Basset Griffon Vendéen', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger des Pyrénées', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Foxhound Anglais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Shiba Inu', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger Américain Miniature', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Chien d’eau Espagnol', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger des Alpes', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bichon Havanais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Keeshond (Spitz Loup)', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Pointer Anglais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Boston Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Bullmastiff', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Border Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Labrador croisé', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Chien du Pharaon', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Barzoï (Lévrier Russe)', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Berger Hollandais', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Puli', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Chien Courant de l’Istrie', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Dogue Argentin', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Basset Hound', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Spinone Italien', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Griffon Bruxellois', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Irish Wolfhound (Lévrier Irlandais)', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Montagne des Pyrénées (Patou)', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Braque de Weimar', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Lagotto Romagnolo', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Dandie Dinmont Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Airedale Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Fox Terrier à Poil Dur', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Petit Lévrier Italien', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Norwich Terrier', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Chien Finnois de Laponie', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Schipperke', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Setter Gordon', UTC_TIMESTAMP(), UTC_TIMESTAMP());


-- 2. Insertion des utilisateurs (ordre d’insertion = id)
INSERT INTO user (firstname, lastname, email, password, user_role, created_at, updated_at)
VALUES ('Stéphane', 'Scheeres', 'super-admin@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'SUPER_ADMIN', UTC_TIMESTAMP() - INTERVAL 10 DAY, UTC_TIMESTAMP() - INTERVAL 7 DAY),
       ('Tetiana', 'Lombardi', 'user@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'OWNER', UTC_TIMESTAMP() - INTERVAL 8 DAY, UTC_TIMESTAMP() - INTERVAL 5 DAY),
       ('Sébastien', 'Scheeres', 'user2@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'OWNER', UTC_TIMESTAMP() - INTERVAL 8 DAY, UTC_TIMESTAMP() - INTERVAL 5 DAY),
       ('Victor', 'Monteragioni', 'admin@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'ADMIN', UTC_TIMESTAMP() - INTERVAL 9 DAY, UTC_TIMESTAMP() - INTERVAL 6 DAY),
       ('Hubert', 'Bonisseur', 'coach@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'COACH', UTC_TIMESTAMP() - INTERVAL 7 DAY, UTC_TIMESTAMP() - INTERVAL 4 DAY),
       ('Larmina', 'El-Akmar', 'larmina@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'COACH', UTC_TIMESTAMP() - INTERVAL 6 DAY, UTC_TIMESTAMP() - INTERVAL 3 DAY),
       ('Jack', 'Jefferson', 'jack@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'COACH', UTC_TIMESTAMP() - INTERVAL 5 DAY, UTC_TIMESTAMP() - INTERVAL 2 DAY),
       ('Princess', 'Al-Tarouk', 'princess@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'COACH', UTC_TIMESTAMP() - INTERVAL 4 DAY, UTC_TIMESTAMP() - INTERVAL 1 DAY);

-- Nouveau owner pour augmenter les places dans les cours
INSERT INTO user (firstname, lastname, email, password, user_role, created_at, updated_at)
VALUES ('Paul', 'Durand', 'paul.durand@k9club.fr', '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'OWNER', UTC_TIMESTAMP() - INTERVAL 7 DAY, UTC_TIMESTAMP() - INTERVAL 1 DAY),
       ('Julie', 'Martin', 'julie.martin@k9club.fr', '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'OWNER', UTC_TIMESTAMP() - INTERVAL 5 DAY, UTC_TIMESTAMP()),
       ('Louis', 'Petit', 'louis.petit@k9club.fr', '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'OWNER', UTC_TIMESTAMP() - INTERVAL 3 DAY, UTC_TIMESTAMP());


-- 4. Chiens (Rex = adulte ~6 ans, Mia = chiot 1 an)
INSERT INTO dog (name, birthdate, gender, avatar_url, created_at, user_id, breed_id)
VALUES ('Rex', '2018-06-01', 'male', 'golden.png', UTC_TIMESTAMP(), 2, 1),
       ('Mia', '2023-06-01', 'female', 'bouledogue.png', UTC_TIMESTAMP(), 2, 2);

-- Paul
INSERT INTO dog (name, birthdate, gender, avatar_url, created_at, user_id, breed_id)
VALUES ('Fidji', '2019-03-14', 'male', 'berger-allemand.png', UTC_TIMESTAMP(),
        (SELECT id FROM user WHERE email = 'paul.durand@k9club.fr'), 2),
       ('Bella', '2021-09-21', 'female', 'caniche.png', UTC_TIMESTAMP(),
        (SELECT id FROM user WHERE email = 'paul.durand@k9club.fr'), 10);

-- Julie
INSERT INTO dog (name, birthdate, gender, avatar_url, created_at, user_id, breed_id)
VALUES ('Tango', '2020-11-11', 'male', 'doberman.png', UTC_TIMESTAMP(),
        (SELECT id FROM user WHERE email = 'julie.martin@k9club.fr'), 8),
       ('Nina', '2023-01-08', 'female', 'carlin.png', UTC_TIMESTAMP(),
        (SELECT id FROM user WHERE email = 'julie.martin@k9club.fr'), 3);

-- Louis
INSERT INTO dog (name, birthdate, gender, avatar_url, created_at, user_id, breed_id)
VALUES ('Rocky', '2018-07-02', 'male', 'rottweiler.png', UTC_TIMESTAMP(),
        (SELECT id FROM user WHERE email = 'louis.petit@k9club.fr'), 5),
       ('Moka', '2022-04-22', 'female', 'shiba.png', UTC_TIMESTAMP(),
        (SELECT id FROM user WHERE email = 'louis.petit@k9club.fr'), 12);


-- 5. Types de cours (course_type_id = ordre d’insertion)
INSERT INTO course_type (name, text_color, background_color, created_at, updated_at)
VALUES ('artistique', '#831F00', '#FAD7CC', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('agilité', '#724300', '#FAE7CC', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('base', '#635900', '#FEF8C5', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('ring', '#006E11', '#CCF2D2', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('canicross', '#005671', '#CCEFFA', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('détection', '#420075', '#E6CCFA', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('chiot', '#000000', '#F4F4F4', UTC_TIMESTAMP(), UTC_TIMESTAMP());

-- 6. Tranches d'âge (age_range_id = 1, 2, 3)
INSERT INTO age_range (min_age, max_age, created_at, updated_at)
VALUES (0, 2, UTC_TIMESTAMP(), UTC_TIMESTAMP()), -- 1: chiots (Mia)
       -- 2: juniors (Rex commence à être éligible dès 3 ans)
       (3, 5, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       -- 3: adultes (Rex)
       (6, 20, UTC_TIMESTAMP(), UTC_TIMESTAMP());


-- 7. Création de cours
-- Génère 15 cours pour Rex (10 sans résa, 5 déjà réservés)
-- Génère 15 cours pour Mia (idem)

-- Cours pour Rex (tranche d’âge adulte : age_range_id=3, commence à 6 ans, donc tous cours de 6 à 20 ans)
-- Pour couvrir le cas, on va réserver les 5 premiers et laisser les autres libres
INSERT INTO course (name, description, max_participants, start_date, end_date, created_at, updated_at, user_id,
                    course_type_id, age_range_id)
VALUES
-- 5 cours réservés (passé, aujourd'hui, futur proche)
('Agilité adulte passé', 'Cours adulte pour Rex passé', 8, DATE_SUB(CONCAT(CURDATE(), ' 09:00:00'), INTERVAL 10 DAY),
 DATE_SUB(CONCAT(CURDATE(), ' 10:00:00'), INTERVAL 10 DAY), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 2, 3),
('Base adulte passé', 'Cours base adulte Rex passé', 8, DATE_SUB(CONCAT(CURDATE(), ' 15:30:00'), INTERVAL 7 DAY),
 DATE_SUB(CONCAT(CURDATE(), ' 16:30:00'), INTERVAL 7 DAY), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 3, 3),
('Détection adulte aujourd\'hui', 'Cours adulte pour Rex today', 8, CONCAT(CURDATE(), ' 13:00:00'),
 CONCAT(CURDATE(), ' 14:30:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 6, 3),
('Ring adulte futur proche', 'Cours ring adulte Rex', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 1 DAY), ' 17:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 1 DAY), ' 18:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 4, 3),
('Artistique adulte futur', 'Cours artistique adulte Rex', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 3 DAY), ' 08:30:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 3 DAY), ' 09:30:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 1, 3),
-- 10 cours disponibles (futurs, pas de réservation Rex)
('Agilité adulte dispo 1',
 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus nec arcu arcu. Etiam commodo egestas rutrum. Donec in suscipit risus, sed consectetur turpis. Donec et bibendum libero, ut viverra felis. Fusce eget vehicula lectus. Etiam sagittis magna sit amet ipsum fermentum tempus. In tempus enim consequat dapibus maximus. Nulla nunc leo, molestie et diam eget, vehicula placerat mauris.',
 5, CONCAT(DATE_ADD(CURDATE(), INTERVAL 4 DAY), ' 10:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 4 DAY), ' 11:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 2, 3),
('Agilité adulte dispo 2',
 'Aliquam a velit nec sapien lobortis porta. Praesent vel velit elementum, tincidunt lorem sit amet, congue magna. Duis interdum orci quis elementum pulvinar. Morbi urna felis, ornare vel dapibus nec, rhoncus posuere est. Vestibulum dictum orci id odio scelerisque pellentesque. Nullam lobortis rutrum libero, vel mattis libero ornare vel. Morbi eget urna porta, euismod lacus consequat, vestibulum erat. Nulla iaculis lacus mi, vel convallis dui hendrerit vitae.',
 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 5 DAY), ' 15:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 5 DAY), ' 16:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 2, 3),
('Détection adulte dispo 1',
 'Aliquam a velit nec sapien lobortis porta. Praesent vel velit elementum, tincidunt lorem sit amet, congue magna. Duis interdum orci quis elementum pulvinar. Morbi urna felis, ornare vel dapibus nec, rhoncus posuere est. Vestibulum dictum orci id odio scelerisque pellentesque. Nullam lobortis rutrum libero, vel mattis libero ornare vel. Morbi eget urna porta, euismod lacus consequat, vestibulum erat. Nulla iaculis lacus mi, vel convallis dui hendrerit vitae.',
 2, CONCAT(DATE_ADD(CURDATE(), INTERVAL 6 DAY), ' 18:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 6 DAY), ' 19:30:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 6, 3),
('Détection adulte dispo 2',
 'Aliquam a velit nec sapien lobortis porta. Praesent vel velit elementum, tincidunt lorem sit amet, congue magna. Duis interdum orci quis elementum pulvinar. Morbi urna felis, ornare vel dapibus nec, rhoncus posuere est. Vestibulum dictum orci id odio scelerisque pellentesque. Nullam lobortis rutrum libero, vel mattis libero ornare vel. Morbi eget urna porta, euismod lacus consequat, vestibulum erat. Nulla iaculis lacus mi, vel convallis dui hendrerit vitae.',
 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 7 DAY), ' 11:15:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 7 DAY), ' 12:15:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 6, 3),
('Ring adulte dispo 1',
 'Aliquam a velit nec sapien lobortis porta. Praesent vel velit elementum, tincidunt lorem sit amet, congue magna. Duis interdum orci quis elementum pulvinar. Morbi urna felis, ornare vel dapibus nec, rhoncus posuere est. Vestibulum dictum orci id odio scelerisque pellentesque. Nullam lobortis rutrum libero, vel mattis libero ornare vel. Morbi eget urna porta, euismod lacus consequat, vestibulum erat. Nulla iaculis lacus mi, vel convallis dui hendrerit vitae.',
 4, CONCAT(DATE_ADD(CURDATE(), INTERVAL 8 DAY), ' 14:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 8 DAY), ' 15:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 4, 3),
('Ring adulte dispo 2',
 'Aliquam a velit nec sapien lobortis porta. Praesent vel velit elementum, tincidunt lorem sit amet, congue magna. Duis interdum orci quis elementum pulvinar. Morbi urna felis, ornare vel dapibus nec, rhoncus posuere est. Vestibulum dictum orci id odio scelerisque pellentesque. Nullam lobortis rutrum libero, vel mattis libero ornare vel. Morbi eget urna porta, euismod lacus consequat, vestibulum erat. Nulla iaculis lacus mi, vel convallis dui hendrerit vitae.',
 7, CONCAT(DATE_ADD(CURDATE(), INTERVAL 9 DAY), ' 16:30:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 9 DAY), ' 17:30:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 4, 3),
('Artistique adulte dispo 1',
 'Aliquam a velit nec sapien lobortis porta. Praesent vel velit elementum, tincidunt lorem sit amet, congue magna. Duis interdum orci quis elementum pulvinar. Morbi urna felis, ornare vel dapibus nec, rhoncus posuere est. Vestibulum dictum orci id odio scelerisque pellentesque. Nullam lobortis rutrum libero, vel mattis libero ornare vel. Morbi eget urna porta, euismod lacus consequat, vestibulum erat. Nulla iaculis lacus mi, vel convallis dui hendrerit vitae.',
 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 10 DAY), ' 08:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 10 DAY), ' 09:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 1, 3),
('Artistique adulte dispo 2',
 'Aliquam a velit nec sapien lobortis porta. Praesent vel velit elementum, tincidunt lorem sit amet, congue magna. Duis interdum orci quis elementum pulvinar. Morbi urna felis, ornare vel dapibus nec, rhoncus posuere est. Vestibulum dictum orci id odio scelerisque pellentesque. Nullam lobortis rutrum libero, vel mattis libero ornare vel. Morbi eget urna porta, euismod lacus consequat, vestibulum erat. Nulla iaculis lacus mi, vel convallis dui hendrerit vitae.',
 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 11 DAY), ' 19:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 11 DAY), ' 20:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 1, 3),
('Base adulte dispo 1',
 'Aliquam a velit nec sapien lobortis porta. Praesent vel velit elementum, tincidunt lorem sit amet, congue magna. Duis interdum orci quis elementum pulvinar. Morbi urna felis, ornare vel dapibus nec, rhoncus posuere est. Vestibulum dictum orci id odio scelerisque pellentesque. Nullam lobortis rutrum libero, vel mattis libero ornare vel. Morbi eget urna porta, euismod lacus consequat, vestibulum erat. Nulla iaculis lacus mi, vel convallis dui hendrerit vitae.',
 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 12 DAY), ' 17:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 12 DAY), ' 18:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 3, 3),
('Base adulte dispo 2',
 'Aliquam a velit nec sapien lobortis porta. Praesent vel velit elementum, tincidunt lorem sit amet, congue magna. Duis interdum orci quis elementum pulvinar. Morbi urna felis, ornare vel dapibus nec, rhoncus posuere est. Vestibulum dictum orci id odio scelerisque pellentesque. Nullam lobortis rutrum libero, vel mattis libero ornare vel. Morbi eget urna porta, euismod lacus consequat, vestibulum erat. Nulla iaculis lacus mi, vel convallis dui hendrerit vitae.',
 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 13 DAY), ' 13:30:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 13 DAY), ' 14:30:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 3, 3);

-- Cours pour Mia (tranche d’âge chiot : age_range_id=1, 0 à 2 ans)
INSERT INTO course (name, description, max_participants, start_date, end_date, created_at, updated_at, user_id,
                    course_type_id, age_range_id)
VALUES
-- 5 cours réservés
('Chiot passé 1', 'Cours chiot Mia passé', 8, DATE_SUB(CONCAT(CURDATE(), ' 11:00:00'), INTERVAL 8 DAY),
 DATE_SUB(CONCAT(CURDATE(), ' 12:00:00'), INTERVAL 8 DAY), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 7, 1),
('Chiot passé 2', 'Cours chiot Mia passé', 8, DATE_SUB(CONCAT(CURDATE(), ' 14:30:00'), INTERVAL 5 DAY),
 DATE_SUB(CONCAT(CURDATE(), ' 15:30:00'), INTERVAL 5 DAY), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 7, 1),
('Chiot today', 'Cours chiot aujourd\'hui', 8, CONCAT(CURDATE(), ' 10:00:00'), CONCAT(CURDATE(), ' 11:30:00'),
 UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 7, 1),
('Agilité chiot futur', 'Cours agilité chiot', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 2 DAY), ' 17:30:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 2 DAY), ' 18:30:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 2, 1),
('Base chiot futur', 'Cours base chiot', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 4 DAY), ' 08:15:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 4 DAY), ' 09:15:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 3, 1),
-- 10 cours disponibles (futurs, pas de réservation Mia)
('Chiot dispo 1', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 5 DAY), ' 13:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 5 DAY), ' 14:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 7, 1),
('Chiot dispo 2', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 6 DAY), ' 15:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 6 DAY), ' 16:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 7, 1),
('Agilité chiot dispo 1', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 7 DAY), ' 11:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 7 DAY), ' 12:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 2, 1),
('Agilité chiot dispo 2', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 8 DAY), ' 16:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 8 DAY), ' 17:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 2, 1),
('Base chiot dispo 1', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 9 DAY), ' 17:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 9 DAY), ' 18:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 3, 1),
('Base chiot dispo 2', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 10 DAY), ' 18:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 10 DAY), ' 19:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 3, 1),
('Détection chiot dispo 1', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 11 DAY), ' 10:30:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 11 DAY), ' 11:30:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 6, 1),
('Détection chiot dispo 2', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 12 DAY), ' 09:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 12 DAY), ' 10:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 6, 1),
('Artistique chiot dispo 1', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 13 DAY), ' 13:30:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 13 DAY), ' 14:30:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 1, 1),
('Artistique chiot dispo 2', 'Cours dispo', 8, CONCAT(DATE_ADD(CURDATE(), INTERVAL 14 DAY), ' 08:00:00'),
 CONCAT(DATE_ADD(CURDATE(), INTERVAL 14 DAY), ' 09:00:00'), UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 1, 1);

-- Réservations Rex (id cours = id d'insertion, donc 1 à 5 pour Rex)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 1),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 2),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 3),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 4),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 5);

-- Réservations Mia (cours_id = id d'insertion: après ceux de Rex)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 16),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 17),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 18),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 19),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 20),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 21);

-- Fidji s’inscrit sur "Agilité adulte dispo 1" (id 6) et "Détection adulte dispo 1" (id 8)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        (SELECT id FROM dog WHERE name = 'Fidji'), 6),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        (SELECT id FROM dog WHERE name = 'Fidji'), 8);

-- Bella sur "Agilité adulte dispo 2" (id 7)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        (SELECT id FROM dog WHERE name = 'Bella'), 7);

-- Tango sur "Détection adulte dispo 2" (id 9)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        (SELECT id FROM dog WHERE name = 'Tango'), 9);

-- Nina sur "Ring adulte dispo 1" (id 10)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        (SELECT id FROM dog WHERE name = 'Nina'), 10);

-- Rocky sur "Ring adulte dispo 2" (id 11) et "Artistique adulte dispo 1" (id 12)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        (SELECT id FROM dog WHERE name = 'Rocky'), 11),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        (SELECT id FROM dog WHERE name = 'Rocky'), 12);

-- Moka sur "Base adulte dispo 1" (id 13) et "Base adulte dispo 2" (id 14)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        (SELECT id FROM dog WHERE name = 'Moka'), 13),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(),
        (SELECT id FROM dog WHERE name = 'Moka'), 14);
-- Fidji (Paul) sur Agilité adulte dispo 1, Détection adulte dispo 1, Artistique adulte dispo 1
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Fidji'), 6),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Fidji'), 8),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Fidji'), 12);

-- Bella (Paul) sur Agilité adulte dispo 1 et Ring adulte dispo 2
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Bella'), 6),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Bella'), 11);

-- Tango (Julie) sur Agilité adulte dispo 2 et Détection adulte dispo 2
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Tango'), 7),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Tango'), 9);

-- Nina (Julie) sur Ring adulte dispo 1 et Artistique adulte dispo 2
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Nina'), 10),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Nina'), 13);

-- Rocky (Louis) sur Base adulte dispo 1 et 2
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Rocky'), 14),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Rocky'), 15);

-- Moka (Louis) sur Artistique adulte dispo 2, Base adulte dispo 1
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Moka'), 13),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Moka'), 14);

-- Fidji (Paul) sur Chiot dispo 1, Agilité chiot dispo 2
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Fidji'), 21),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Fidji'), 24);

-- Bella (Paul) sur Chiot dispo 2
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Bella'), 22);

-- Tango (Julie) sur Agilité chiot dispo 1 et Détection chiot dispo 2
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Tango'), 23),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Tango'), 27);

-- Nina (Julie) sur Base chiot dispo 2
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Nina'), 26);

-- Rocky (Louis) sur Détection chiot dispo 1
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Rocky'), 27);

-- Moka (Louis) sur Artistique chiot dispo 1 et Chiot dispo 2
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Moka'), 29),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), (SELECT id FROM dog WHERE name = 'Moka'), 22);
