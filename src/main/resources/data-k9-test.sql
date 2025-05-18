-- 1. Insertion des 100 races (breed) avec noms en français
INSERT INTO breed (name)
VALUES ('Bouledogue Français'),
       ('Labrador Retriever'),
       ('Golden Retriever'),
       ('Berger Allemand'),
       ('Caniche'),
       ('Teckel'),
       ('Bouledogue Anglais'),
       ('Beagle'),
       ('Rottweiler'),
       ('Braque Allemand à poil court'),
       ('Welsh Corgi Pembroke'),
       ('Berger Australien'),
       ('Yorkshire Terrier'),
       ('Cavalier King Charles'),
       ('Dobermann'),
       ('Cane Corso'),
       ('Schnauzer nain'),
       ('Husky Sibérien'),
       ('Shih Tzu'),
       ('Grand Danois'),
       ('Boston Terrier'),
       ('Bouvier Bernois'),
       ('Spitz nain'),
       ('Bichon Havanais'),
       ('Colley du Shetland'),
       ('Épagneul Breton'),
       ('Springer Anglais'),
       ('Cocker Anglais'),
       ('Berger Américain Miniature'),
       ('Border Collie'),
       ('Vizsla'),
       ('Chihuahua'),
       ('Dogue'),
       ('Carlin'),
       ('Bichon Maltais'),
       ('Braque de Weimar'),
       ('Terre-Neuve'),
       ('Ridgeback Rhodésien'),
       ('West Highland White Terrier'),
       ('Bichon Frisé'),
       ('Malinois'),
       ('Colley'),
       ('Retriever de la baie de Chesapeake'),
       ('Shiba Inu'),
       ('Saint-Bernard'),
       ('Akita Inu'),
       ('Bloodhound'),
       ('Bull Terrier'),
       ('Whippet'),
       ('Australian Cattle Dog'),
       ('Basset Hound'),
       ('Malamute de l’Alaska'),
       ('Terrier Irlandais à poil doux'),
       ('Dalmatien'),
       ('Samoyède'),
       ('Scottish Terrier'),
       ('Staffordshire Bull Terrier'),
       ('Setter Irlandais'),
       ('Cairn Terrier'),
       ('Chien d\'eau Portugais'),
       ('Patou'),
       ('Berger Picard'),
       ('Tervueren'),
       ('Keeshond'),
       ('Léonberger'),
       ('Schnauzer Standard'),
       ('Chien de berger d\'Anatolie'),
       ('Retriever à poil plat'),
       ('Pékinois'),
       ('Setter Anglais'),
       ('Terrier Tibétain'),
       ('Chien d\'élan Norvégien'),
       ('Griffon Bruxellois'),
       ('Lagotto Romagnolo'),
       ('Boykin Spaniel'),
       ('Boxer'),
       ('American Pit Bull Terrier'),
       ('Cocker Anglais'),
       ('American Staffordshire Terrier'),
       ('Basenji'),
       ('Berger Belge'),
       ('Border Terrier'),
       ('Bullmastiff'),
       ('Chow-Chow'),
       ('Shar-Peï Chinois'),
       ('Dogue de Bordeaux'),
       ('Foxhound Anglais'),
       ('Mastiff Anglais'),
       ('Spaniel nain anglais'),
       ('Lévrier Irlandais'),
       ('Lévrier Italien'),
       ('Chin Japonais'),
       ('Lhassa Apso'),
       ('Pinscher nain'),
       ('Papillon'),
       ('Chien du Pharaon'),
       ('Saluki'),
       ('Schipperke'),
       ('Sealyham Terrier'),
       ('Spinone Italien')
;

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
    ('Larmina', 'El-Akmar Betouche', 'larmina@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 6 DAY,
     UTC_TIMESTAMP() - INTERVAL 3 DAY),
    ('Jack', 'Jefferson', 'jack@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 5 DAY,
     UTC_TIMESTAMP() - INTERVAL 2 DAY),
    ('Princess', 'Al-Tarouk', 'princess@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 4 DAY,
     UTC_TIMESTAMP() - INTERVAL 1 DAY),
    ('Dolorès', 'Koulechov', 'dolores@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 3 DAY,
     UTC_TIMESTAMP()),
    ('Heinrich', 'Von-Zimmel', 'zimmel@k9club.fr',
     '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
     'COACH',
     UTC_TIMESTAMP() - INTERVAL 2 DAY,
     UTC_TIMESTAMP() + INTERVAL 1 HOUR);

-- 2. Ajout de deux nouveaux OWNER
INSERT INTO user (firstname, lastname, email, password, user_role, created_at, updated_at)
VALUES ('Alice', 'Martin', 'martin@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'OWNER', UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Jean-Pierre', 'Leblanc', 'leblanc@k9club.fr',
        '$2a$10$UT44bkoGz/wOFFXrAKijOe/xu3W1uPTAEjv6dZx6cj0NEN2o7JCDO',
        'OWNER', UTC_TIMESTAMP(), UTC_TIMESTAMP());

-- 3. Création de chiens pour tous les OWNER
INSERT INTO dog (name, birthday, gender, created_at, user_id, breed_id)
VALUES
    -- Pour Tetiana Lombardi (2 chiens)
    ('Rex', '2019-06-01', 'Male', UTC_TIMESTAMP(),
     (SELECT id FROM user WHERE email = 'user@k9club.fr'),
     (SELECT id FROM breed WHERE name = 'Berger Allemand')),
    ('Mia', '2021-02-14', 'Female', UTC_TIMESTAMP(),
     (SELECT id FROM user WHERE email = 'user@k9club.fr'),
     (SELECT id FROM breed WHERE name = 'Golden Retriever')),

    -- Pour Alice Martin
    ('Charlie', '2020-11-20', 'Male', UTC_TIMESTAMP(),
     (SELECT id FROM user WHERE email = 'martin@k9club.fr'),
     (SELECT id FROM breed WHERE name = 'Beagle')),
    ('Daisy', '2022-05-05', 'Female', UTC_TIMESTAMP(),
     (SELECT id FROM user WHERE email = 'martin@k9club.fr'),
     (SELECT id FROM breed WHERE name = 'Labrador Retriever')),

    -- Pour Jean-Pierre Leblanc
    ('Max', '2018-03-10', 'Male', UTC_TIMESTAMP(),
     (SELECT id FROM user WHERE email = 'leblanc@k9club.fr'),
     (SELECT id FROM breed WHERE name = 'Border Collie'));

-- 4. Créations des différents types pour les cours
INSERT INTO course_type (name, text_color, background_color)
VALUES ('artistique', '#831F00', '#FAD7CC'),
       ('agilité', '#724300', '#FAE7CC'),
       ('base', '#635900', '#FEF8C5'),
       ('ring', '#006E11', '#CCF2D2'),
       ('canicross', '#005671', '#CCEFFA'),
       ('détection', '#420075', '#E6CCFA'),
       ('chiot', '#000000', '#F4F4F4');


-- 5. Insertion des tranches d'âge avec timestamps
INSERT INTO age_range (min_age, max_age, created_at, updated_at)
VALUES (0, 2, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (3, 5, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (6, 15, UTC_TIMESTAMP(), UTC_TIMESTAMP());


-- 6. Création de cours avec coach, type et tranche d'âge
INSERT INTO course (name,
                    description,
                    max_participants,
                    start_date,
                    end_date,
                    created_at,
                    updated_at,
                    user_id,
                    course_type_id,
                    age_range_id)
VALUES ('Pouponnière Canine',
        'Initiation aux ordres de base pour chiens de 0 à 2 ans.',
        8,
        '2025-05-20 10:00:00',
        '2025-05-20 11:30:00',
        UTC_TIMESTAMP(), UTC_TIMESTAMP(),
           -- Coach Hubert
        (SELECT id FROM user WHERE email = 'coach@k9club.fr'),
           -- Type chiot
        (SELECT id FROM course_type WHERE name = 'chiot'),
           -- Tranche 0–2 ans
        (SELECT id FROM age_range WHERE min_age = 0 AND max_age = 2)),
       ('Agilité Débutant',
        'Découverte de l’agilité pour chiens de 3 à 5 ans.',
        10,
        '2025-05-22 14:00:00',
        '2025-05-22 15:30:00',
        UTC_TIMESTAMP(), UTC_TIMESTAMP(),
           -- Coach Larmina
        (SELECT id FROM user WHERE email = 'larmina@k9club.fr'),
           -- Type agilité
        (SELECT id FROM course_type WHERE name = 'agilité'),
           -- Tranche 3–5 ans
        (SELECT id FROM age_range WHERE min_age = 3 AND max_age = 5)),
       ('Détection Avancée',
        'Perfectionnement à la détection pour chiens de 6 ans et plus.',
        6,
        '2025-05-25 09:00:00',
        '2025-05-25 11:00:00',
        UTC_TIMESTAMP(), UTC_TIMESTAMP(),
           -- Coach Jack
        (SELECT id FROM user WHERE email = 'jack@k9club.fr'),
           -- Type détection
        (SELECT id FROM course_type WHERE name = 'détection'),
           -- Tranche 6–15 ans
        (SELECT id FROM age_range WHERE min_age = 6 AND max_age = 15)),
       ('Canicross Découverte',
        'Premiers pas en canicross pour chiens de 3 à 5 ans.',
        12,
        '2025-05-27 08:00:00',
        '2025-05-27 09:30:00',
        UTC_TIMESTAMP(), UTC_TIMESTAMP(),
           -- Coach Princess
        (SELECT id FROM user WHERE email = 'princess@k9club.fr'),
           -- Type canicross
        (SELECT id FROM course_type WHERE name = 'canicross'),
           -- Tranche 3–5 ans
        (SELECT id FROM age_range WHERE min_age = 3 AND max_age = 5));

-- 7. Inscriptions des chiens aux cours
INSERT INTO registration (registration_date,
                          status,
                          created_at,
                          updated_at,
                          dog_id,
                          course_id)
VALUES (UTC_TIMESTAMP(),
        'CONFIRMED',
        UTC_TIMESTAMP(),
        UTC_TIMESTAMP(),
           -- Rex (5 ans) en Agilité Débutant
        (SELECT id FROM dog WHERE name = 'Rex'),
        (SELECT id FROM course WHERE name = 'Agilité Débutant')),
       (UTC_TIMESTAMP(),
        'CONFIRMED',
        UTC_TIMESTAMP(),
        UTC_TIMESTAMP(),
           -- Mia (4 ans) en Agilité Débutant
        (SELECT id FROM dog WHERE name = 'Mia'),
        (SELECT id FROM course WHERE name = 'Agilité Débutant')),
       (UTC_TIMESTAMP(),
        'PENDING',
        UTC_TIMESTAMP(),
        UTC_TIMESTAMP(),
           -- Max (7 ans) en Détection Avancée
        (SELECT id FROM dog WHERE name = 'Max'),
        (SELECT id FROM course WHERE name = 'Détection Avancée'));

-- Note : Charlie et Daisy ne sont pas inscrits pour l'instant.
