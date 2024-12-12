@echo off

REM Set PostgreSQL environment variables (Update these as needed)
set PGPASSWORD=demo_psw

REM Check if the database exists before creating it
for /f "delims=" %%A in ('psql -U postgres -h localhost -tc "SELECT 1 FROM pg_database WHERE datname = 'jpa_examples_demo';"') do set DB_EXISTS=%%A
if "%DB_EXISTS%" NEQ "1" (
    psql -U postgres -h localhost -c "CREATE DATABASE jpa_examples_demo;"
    echo Database 'jpa_examples_demo' created.
) else (
    echo Database 'jpa_examples_demo' already exists. Skipping creation.
)

REM Check if the user exists before creating it
for /f "delims=" %%A in ('psql -U postgres -h localhost -tc "SELECT 1 FROM pg_roles WHERE rolname = 'demo_user';"') do set USER_EXISTS=%%A
if "%USER_EXISTS%" NEQ "1" (
    psql -U postgres -h localhost -c "CREATE USER demo_user WITH PASSWORD 'password';"
    echo User 'demo_user' created.
) else (
    echo User 'demo_user' already exists. Skipping creation.
)

REM Grant privileges to the user
psql -U postgres -h localhost -c "GRANT ALL PRIVILEGES ON DATABASE jpa_examples_demo TO demo_user;"

REM Execute the SQL script to set up tables and insert sample data
psql -U demo_user -h localhost -d jpa_examples_demo -f jpa_examples_demo.sql

@echo PostgreSQL setup completed successfully!
pause
