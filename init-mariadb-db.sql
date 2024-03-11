CREATE DATABASE IF NOT EXISTS api;
USE api;

CREATE TABLE api_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO api_user (username, email, password) VALUES 
('user1', 'user1@example.com', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b'),
('user2', 'user2@example.com', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35'),
('user3', 'user3@example.com', '4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce');