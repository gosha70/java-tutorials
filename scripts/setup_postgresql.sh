#!/bin/bash

# Set PostgreSQL password for the postgres user
export PGPASSWORD="demo_psw"

# Check if the database exists before creating it
DB_EXISTS=$(psql -U postgres -h localhost -tc "SELECT 1 FROM pg_database WHERE datname = 'jpa_examples_demo';")
if [ "$DB_EXISTS" != "1" ]; then
    psql -U postgres -h localhost -c "CREATE DATABASE jpa_examples_demo;"
    echo "Database 'jpa_examples_demo' created."
else
    echo "Database 'jpa_examples_demo' already exists. Skipping creation."
fi

# Check if the user exists before creating it
USER_EXISTS=$(psql -U postgres -h localhost -tc "SELECT 1 FROM pg_roles WHERE rolname = 'demo_user';")
if [ "$USER_EXISTS" != "1" ]; then
    psql -U postgres -h localhost -c "CREATE USER demo_user WITH PASSWORD 'password';"
    echo "User 'demo_user' created."
else
    echo "User 'demo_user' already exists. Skipping creation."
fi

# Grant privileges to the user
psql -U postgres -h localhost -c "GRANT ALL PRIVILEGES ON DATABASE jpa_examples_demo TO demo_user;"

# Execute the SQL script to set up tables and insert sample data
psql -U demo_user -h localhost -d jpa_examples_demo -f jpa_examples_demo.sql

echo "PostgreSQL setup completed successfully!"
