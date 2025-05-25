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

-- 3. Insertion des chiens (dog_id = 1 pour Rex, 2 pour Mia)
INSERT INTO dog (name, birthdate, gender, created_at, user_id, breed_id)
VALUES ('Rex', '2019-06-01', 'Male', UTC_TIMESTAMP(), 2, 1),
       ('Mia', '2021-02-14', 'Female', UTC_TIMESTAMP(), 2, 2);

-- 4. Types de cours (course_type_id = ordre d’insertion)
INSERT INTO course_type (name, text_color, background_color, created_at, updated_at)
VALUES ('artistique', '#831F00', '#FAD7CC', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('agilité', '#724300', '#FAE7CC', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('base', '#635900', '#FEF8C5', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('ring', '#006E11', '#CCF2D2', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('canicross', '#005671', '#CCEFFA', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('détection', '#420075', '#E6CCFA', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('chiot', '#000000', '#F4F4F4', UTC_TIMESTAMP(), UTC_TIMESTAMP());

-- 5. Tranches d'âge (age_range_id = 1, 2, 3)
INSERT INTO age_range (min_age, max_age, created_at, updated_at)
VALUES (0, 2, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (3, 5, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (6, 15, UTC_TIMESTAMP(), UTC_TIMESTAMP());

-- 6. Création des cours (id = ordre d’insertion, donc Rex : 1-10, Mia : 11-20)
-- Les cours de Rex
INSERT INTO course (name, description, max_participants, start_date, end_date, created_at, updated_at, user_id,
                    course_type_id, age_range_id)
VALUES ('Pouponnière Canine', 'Ordres de base pour chiots.', 8, DATE_SUB(CURDATE(), INTERVAL 30 DAY),
        DATE_SUB(CURDATE(), INTERVAL 30 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 3, 7, 1),
       ('Agility Pro', 'Parcours d’agility avancé.', 10, DATE_SUB(CURDATE(), INTERVAL 25 DAY),
        DATE_SUB(CURDATE(), INTERVAL 25 DAY) + INTERVAL 2 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 2, 2),
       ('Détection Avancée', 'Entraînement à la détection.', 6, DATE_SUB(CURDATE(), INTERVAL 20 DAY),
        DATE_SUB(CURDATE(), INTERVAL 20 DAY) + INTERVAL 2 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 6, 3),
       ('Base Loisirs', 'Jeux et obéissance.', 9, DATE_SUB(CURDATE(), INTERVAL 15 DAY),
        DATE_SUB(CURDATE(), INTERVAL 15 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 3, 2),
       ('Canicross Fun', 'Initiation au canicross.', 10, DATE_SUB(CURDATE(), INTERVAL 10 DAY),
        DATE_SUB(CURDATE(), INTERVAL 10 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 3, 5, 2),
       ('Journée Chien Zen', 'Cours d’éducation et de relaxation.', 8, CURDATE(), CURDATE() + INTERVAL 2 HOUR,
        UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 1, 1),
       ('Ring Découverte', 'Exercices de ring niveau débutant.', 8, DATE_ADD(CURDATE(), INTERVAL 3 DAY),
        DATE_ADD(CURDATE(), INTERVAL 3 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 5, 2),
       ('Olfaction & Recherche', 'Jeux olfactifs avancés.', 8, DATE_ADD(CURDATE(), INTERVAL 5 DAY),
        DATE_ADD(CURDATE(), INTERVAL 5 DAY) + INTERVAL 2 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 6, 3),
       ('Agility Master', 'Agility niveau expert.', 7, DATE_ADD(CURDATE(), INTERVAL 8 DAY),
        DATE_ADD(CURDATE(), INTERVAL 8 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 3, 2, 2),
       ('Socialisation Parc', 'Rencontres et socialisation.', 12, DATE_ADD(CURDATE(), INTERVAL 12 DAY),
        DATE_ADD(CURDATE(), INTERVAL 12 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 3, 1);

-- Les cours de Mia (tous différents de Rex sauf Journée Chien Zen)
INSERT INTO course (name, description, max_participants, start_date, end_date, created_at, updated_at, user_id,
                    course_type_id, age_range_id)
VALUES ('Obéissance Puppy', 'Obéissance de base chiot.', 8, DATE_SUB(CURDATE(), INTERVAL 28 DAY),
        DATE_SUB(CURDATE(), INTERVAL 28 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 7, 1),
       ('Agility Initiation', 'Découverte de l’agility.', 10, DATE_SUB(CURDATE(), INTERVAL 22 DAY),
        DATE_SUB(CURDATE(), INTERVAL 22 DAY) + INTERVAL 2 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 3, 2, 2),
       ('Détection Fun', 'Détection ludique.', 6, DATE_SUB(CURDATE(), INTERVAL 18 DAY),
        DATE_SUB(CURDATE(), INTERVAL 18 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 6, 3),
       ('Education Citadine', 'Eduquer son chien en ville.', 9, DATE_SUB(CURDATE(), INTERVAL 13 DAY),
        DATE_SUB(CURDATE(), INTERVAL 13 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 3, 2),
       ('Canicross Découverte', 'Canicross niveau débutant.', 10, DATE_SUB(CURDATE(), INTERVAL 8 DAY),
        DATE_SUB(CURDATE(), INTERVAL 8 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 3, 5, 2),
       ('Journée Chien Zen', 'Cours d’éducation et de relaxation.', 8, CURDATE(), CURDATE() + INTERVAL 2 HOUR,
        UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 1, 1),
       ('Ring Initiation', 'Exercices de ring débutants.', 8, DATE_ADD(CURDATE(), INTERVAL 4 DAY),
        DATE_ADD(CURDATE(), INTERVAL 4 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 5, 4, 2),
       ('Pistage Nature', 'Jeux de piste en extérieur.', 8, DATE_ADD(CURDATE(), INTERVAL 7 DAY),
        DATE_ADD(CURDATE(), INTERVAL 7 DAY) + INTERVAL 2 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 6, 3),
       ('Agility Amusant', 'Agility pour tous.', 7, DATE_ADD(CURDATE(), INTERVAL 10 DAY),
        DATE_ADD(CURDATE(), INTERVAL 10 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 3, 2, 2),
       ('Dog Dancing', 'Danse et tricks.', 12, DATE_ADD(CURDATE(), INTERVAL 15 DAY),
        DATE_ADD(CURDATE(), INTERVAL 15 DAY) + INTERVAL 1 HOUR, UTC_TIMESTAMP(), UTC_TIMESTAMP(), 6, 1, 1);

-- 7. Inscriptions de Rex (dog_id = 1, cours 1 à 10)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 1),
       ('CANCELLED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 2),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 3),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 4),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 5),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 6),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 7),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 8),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 9),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 10);

-- 8. Inscriptions de Mia (dog_id = 2, cours 11 à 20 sauf le 16 qui est aussi le 6)
INSERT INTO course_registration (status, created_at, updated_at, dog_id, course_id)
VALUES ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 11),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 12),
       ('CANCELLED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 13),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 14),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 15),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 10), -- Journée Chien Zen, même cours commun, id 6
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 17),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 18),
       ('PENDING', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 19),
       ('CONFIRMED', UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 20);
