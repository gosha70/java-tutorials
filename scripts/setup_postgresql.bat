@echo off

REM Set PostgreSQL environment variables (Update these as needed)
set PGPASSWORD=demo_psw

REM Connect to PostgreSQL and create the database
psql -U postgres -h localhost -c "CREATE DATABASE jpa_examples_demo;"

REM Create a new user
psql -U postgres -h localhost -c "CREATE USER demo_user WITH PASSWORD 'password';"

REM Grant privileges to the user
psql -U postgres -h localhost -c "GRANT ALL PRIVILEGES ON DATABASE jpa_examples_demo TO demo_user;"

REM Execute the SQL script to set up tables and insert sample data
psql -U demo_user -h localhost -d jpa_examples_demo -f jpa_examples_demo.sql

@echo PostgreSQL setup completed successfully!
pause