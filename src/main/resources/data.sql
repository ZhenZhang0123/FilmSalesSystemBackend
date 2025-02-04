-- Password for both users is: password123 (BCrypt encoded)
INSERT INTO users (username, email, password, account_non_expired, account_non_locked, credentials_non_expired, is_enabled)
VALUES
    ('admin', 'admin@example.com', '$2y$10$ubHGKjh3/hTm5/7jEzxkd.ROlmIGt/YfFnQiSbxbu2Uoaf93JqrtK', true, true, true, true),
    ('user', 'user@example.com', '$2y$10$ubHGKjh3/hTm5/7jEzxkd.ROlmIGt/YfFnQiSbxbu2Uoaf93JqrtK', true, true, true, true);

INSERT INTO users_authorities (user_id, authorities)
VALUES
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');



