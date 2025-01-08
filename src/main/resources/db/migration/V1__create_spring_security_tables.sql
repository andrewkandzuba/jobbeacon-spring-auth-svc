CREATE TABLE IF NOT EXISTS users (
                       username VARCHAR(50) NOT NULL PRIMARY KEY,
                       password VARCHAR(100) NOT NULL,
                       enabled BOOLEAN NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Record creation timestamp
);

CREATE TABLE IF NOT EXISTS authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Record creation timestamp
                             FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE
);

CREATE UNIQUE INDEX ix_auth_username_authority ON authorities (username, authority);