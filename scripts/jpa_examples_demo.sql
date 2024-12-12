-- Create the schema if it does not exist
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.schemata WHERE schema_name = 'public') THEN
        EXECUTE 'CREATE SCHEMA public';
    END IF;
END $$;

-- Switch to the target schema
SET search_path TO public;

-- Create the development_position table first
CREATE TABLE IF NOT EXISTS development_position (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

-- Create the developer table next
CREATE TABLE IF NOT EXISTS developer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    start_date DATE,
    end_date DATE,
    position_id INT,
    FOREIGN KEY (position_id) REFERENCES development_position (id) ON DELETE SET NULL
);

-- Alter the development_position table to add the foreign key after developer is created
ALTER TABLE development_position
ADD CONSTRAINT fk_developer FOREIGN KEY (developer_id) REFERENCES developer (id) ON DELETE SET NULL;

-- Create the development_skill table
CREATE TABLE IF NOT EXISTS development_skill (
    id SERIAL PRIMARY KEY,
    skill_name VARCHAR(255) NOT NULL
);

-- Create the developer_skills join table
CREATE TABLE IF NOT EXISTS developer_skills (
    developer_id INT NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (developer_id, skill_id),
    FOREIGN KEY (developer_id) REFERENCES developer (id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES development_skill (id) ON DELETE CASCADE
);

-- Create the position_skills join table
CREATE TABLE IF NOT EXISTS position_skills (
    position_id INT NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (position_id, skill_id),
    FOREIGN KEY (position_id) REFERENCES development_position (id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES development_skill (id) ON DELETE CASCADE
);

-- Insert sample data into development_skill
INSERT INTO development_skill (skill_name) 
VALUES 
('Java'), 
('Python'), 
('SQL')
ON CONFLICT DO NOTHING;

-- Insert sample data into developer
INSERT INTO developer (first_name, last_name, start_date, end_date) 
VALUES 
('Alice', 'Smith', '2023-01-01', '2023-12-31'), 
('Bob', 'Johnson', '2023-01-01', '2023-12-31')
ON CONFLICT DO NOTHING;

-- Insert sample data into development_position
INSERT INTO development_position (title) 
VALUES 
('Backend Developer'), 
('Data Analyst')
ON CONFLICT DO NOTHING;

-- Link developers to skills in developer_skills
INSERT INTO developer_skills (developer_id, skill_id) 
VALUES 
(1, 1), -- Alice knows Java
(1, 2), -- Alice knows Python
(2, 2), -- Bob knows Python
(2, 3)  -- Bob knows SQL
ON CONFLICT DO NOTHING;

-- Link positions to prerequisite skills in position_skills
INSERT INTO position_skills (position_id, skill_id) 
VALUES 
(1, 1), -- Backend Developer requires Java
(1, 3), -- Backend Developer requires SQL
(2, 2)  -- Data Analyst requires Python
ON CONFLICT DO NOTHING;

-- Print success message
DO $$ BEGIN
    RAISE NOTICE 'Database schema and tables are ready.';
END $$;
