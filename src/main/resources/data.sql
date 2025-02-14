-- Password for both users is: password123 (BCrypt encoded)
INSERT INTO users (username, email, password, account_non_expired, account_non_locked, credentials_non_expired, is_enabled)
VALUES
    ('admin', 'admin@example.com', '$2y$10$ubHGKjh3/hTm5/7jEzxkd.ROlmIGt/YfFnQiSbxbu2Uoaf93JqrtK', true, true, true, true),
    ('user', 'user@example.com', '$2y$10$ubHGKjh3/hTm5/7jEzxkd.ROlmIGt/YfFnQiSbxbu2Uoaf93JqrtK', true, true, true, true);

INSERT INTO users_authorities (user_id, authorities)
VALUES
    (1, 'ROLE_ADMIN'),
    (1, 'ROLE_USER'),
    (2, 'ROLE_USER');

INSERT INTO shows (film_name, show_time, ticket_price, remaining_tickets, sold_tickets, version)
VALUES
    ('Avatar 2', '2025-02-05 18:00:00', 15.00, 80, 20, 0),
    ('Inception', '2025-02-12 20:00:00', 12.50, 50, 10, 0),
    ('The Matrix', '2025-02-14 19:30:00', 10.00, 200, 50, 0),
    ('Jurassic Park', '2025-02-15 21:00:00', 18.00, 30, 5, 0),
    ('Titanic', '2025-02-17 17:00:00', 14.00, 150, 20, 0),
    ('Interstellar', '2025-02-18 19:00:00', 16.00, 100, 25, 0),
    ('The Dark Knight', '2025-02-20 20:30:00', 13.50, 75, 15, 0),
    ('Forrest Gump', '2025-02-22 18:45:00', 11.00, 120, 30, 0);





