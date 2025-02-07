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

INSERT INTO shows (film_name, show_time, ticket_price, remaining_tickets, sold_tickets)
VALUES
    ('Avatar 2', '2025-02-5 18:00:00', 15.00, 80, 20),
    ('Inception', '2025-02-12 20:00:00', 12.50, 50, 10),
    ('The Matrix', '2025-02-14 19:30:00', 10.00, 200, 50),
    ('Jurassic Park', '2025-02-15 21:00:00', 18.00, 30, 5),
    ('Titanic', '2025-02-17 17:00:00', 14.00, 150, 20);



