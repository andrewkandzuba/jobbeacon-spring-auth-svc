-- Create the T_USER table in PostgreSQL
CREATE TABLE IF NOT EXISTS T_USER (
    user_id SERIAL PRIMARY KEY,     -- Unique identifier for each user, automatically incremented
    user_name VARCHAR(50) NOT NULL UNIQUE, -- Username, must be unique and not null
    password VARCHAR(100) NOT NULL DEFAULT substr(md5(random()::text), 0, 20),  -- Password for the user, required
    granted_authority VARCHAR(255) NOT NULL DEFAULT 'ROLE_USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Record creation timestamp
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Last update timestamp
);

CREATE OR REPLACE FUNCTION update_modified_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;

$$ language 'plpgsql';
CREATE TRIGGER update_modified_time BEFORE UPDATE ON T_USER FOR EACH ROW EXECUTE PROCEDURE update_modified_column();