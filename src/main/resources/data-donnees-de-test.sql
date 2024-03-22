INSERT INTO user_role (role_name)
VALUES ('Chef de Chantier'),
       ('Client'),
       ('Ouvrier'),
       ('Autre');

INSERT INTO user (username, password, role_id)
VALUES ('john_doe', '$2y$13$jqZEQdNccvIA1Hb1dem35.tA29u2OVSofLrgAhJcMP4DWGD7X9vW6', 1),
       ('jane_smith', '$2y$10$Mzwflx1i0KDzIYFrBeRGSuNH7KAMptqlhEEDQIkEm79O6HF2Jh2nm', 2),
       ('celine_user', '$2y$10$rrghgqtnMNGw1Mr03ckdQ.NRFzMA2W7JCw1oGXANHBRUETD/yd7fa',
        3),
       ('other', '$2y$10$rrghgqtnMNGw1Mr03ckdQ.NRFzMA2W7JCw1oGXANHBRUETD/yd7fa', 4);

INSERT INTO chantier (adresse, ouvrier_id, client_id)
VALUES ('impasse de Lebreton', 1, 1),
       ('22, rue de Morin', 2, 2),
       ('342, boulevard de Bonnin', 3, 3);


INSERT INTO task (name, duree_en_minutes)
VALUES ('Déblayer le sol', 60),
       ('Creuser un trou', 120),
       ('Réparer la toiture', 90);

INSERT INTO operation (description, date_debut, date_fin, chantier_id, ouvrier_id, task_id)
VALUES ('Rénover la maison', CURRENT_DATE, '2024-05-10', 1, 1, 2),
       ('Refaire la route', CURRENT_DATE, '2024-11-12', 2, 2, 1),
       ('Finir le bâtiment', CURRENT_DATE, '2024-06-03', 3, 3, 1);

INSERT INTO consumable (name)
VALUES ('Pelle'),
       ('Marteau'),
       ('Echelle');

INSERT INTO task_consumable (consumable_id, task_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 3);