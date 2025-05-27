CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(16) NOT NULL UNIQUE,
    email VARCHAR(200) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    role VARCHAR(20) NOT NULL,
    enabled BOOLEAN NOT NULL
);

INSERT INTO users (username, email, password, `role`, enabled) VALUES
('premium', 'premium@email.com', '$2a$10$a7QLAtm9T3zxBYFmod/61.FfUZ8UeJjKzn3aQRfQq3OrQdr.sAeoa', 'PREMIUM_USER', true),
('alexandre de', 'alexandre@m.com', '$10$sEJzV1GHseQ5qX7SVIfHd.14a0qTvVEXKjzRfSaNVBj0k5qR0ja8y', 'ADMIN', true);