# Lazy Fetch Demo
This project demonstrates strategies to manage lazy-loaded JPA references in business entities using Hibernate. It showcases how to address common challenges associated with lazy fetching and serialization in real-world scenarios.

The application is built as a Maven project in IntelliJ IDEA and integrates with a PostgreSQL database for persistence.

## Prerequisites

Ensure you have the following tools installed on your system:

1. **PostgreSQL** (version 10 or later)
2. **Java Development Kit (JDK)** (version 17 or later)
3. **Apache Maven** (version 3.6 or later)
4. **IntelliJ IDEA** or another Java IDE (optional but recommended)

## Setting Up the PostgreSQL Database

Follow these steps to set up the database:

### Step 1: Install PostgreSQL Locally

If you do not already have PostgreSQL installed on your system, follow these instructions:

#### Windows
1. Download the PostgreSQL installer from the [official PostgreSQL website](https://www.postgresql.org/download/).
2. Run the installer and follow the prompts. During the installation:
   - Select the components to install (ensure "pgAdmin" is selected).
   - Specify a port (default is `5432`).
   - Set a password for the `postgres` user.
3. Once installation is complete, launch the PostgreSQL server from the Start menu or Services.

#### macOS
1. Use Homebrew to install PostgreSQL:
   ```bash
   brew install postgresql
   ```
2. Start the PostgreSQL service:
   ```bash
   brew services start postgresql
   ```
3. Create a default database and user:
   ```bash
   createuser -s postgres
   ```

#### Linux
1. Update your package list and install PostgreSQL:
   ```bash
   sudo apt update
   sudo apt install postgresql postgresql-contrib
   ```
2. Start the PostgreSQL service:
   ```bash
   sudo systemctl start postgresql
   ```
3. Access the PostgreSQL command-line interface:
   ```bash
   sudo -u postgres psql
   ```
   Set a password for the `postgres` user:
   ```sql
   \password postgres
   ```

#### Verify Installation
To verify PostgreSQL is running:
- Run `psql -U postgres` in your terminal or command prompt.
- Use a database GUI like `pgAdmin` to connect to your PostgreSQL server.


### Step 2: Execute the Script

Run the appropriate script for your operating system to set up the database, user, and tables.

## Importing the Project into IntelliJ IDEA

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/gosha70/java-tutorials.git
   ```

2. **Open the Project:**
   - Launch IntelliJ IDEA.
   - Click on `File` -> `Open` and select the `lazy-fetch` module inside the cloned repository.

3. **Import Maven Dependencies:**
   IntelliJ will automatically detect the `pom.xml` file and download the required dependencies.

4. **Configure the Application:**
   - Open `LazyFetchDemoApplication.java`.
   - Verify the `@SpringBootApplication` configuration:

     ```java
     @SpringBootApplication
     @ComponentScan(basePackages = {
         "com.egoge.examples.lazyfetch",
         "com.egoge.json",
         "com.egoge.sdlc"
     })
     @EntityScan(basePackages = "com.egoge.sdlc.entity")
     public class LazyFetchDemoApplication {
         public static void main(String[] args) {
             SpringApplication.run(LazyFetchDemoApplication.class, args);
         }
     }
     ```

5. **Edit Application Properties:**
   Ensure the `src/main/resources/application.properties` file contains the following:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/jpa_examples_demo
   spring.datasource.username=demo_user
   spring.datasource.password=demo_psw
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   server.port=8081
   ```

### Running the Application

1. **Start the Application:**
   - Run the `LazyFetchDemoApplication` class in IntelliJ IDEA.

2. **Access Endpoints:**
   The application provides various REST endpoints under the base URL:  
   `http://localhost:8081/api/lazy-fetch`

   Use a browser or tools like `Postman` to test the following endpoints:

   - **`/dev`**  
     Demonstrates the failure of obtaining lazy-fetched fields outside a `Transaction` and detached Hibernate session:
     ```json
     {
       "status": "error",
       "message": "Error occurred: could not initialize proxy [com.egoge.sdlc.entity.DevelopmentPositionEntity#1] - no Session"
     }
     ```

   - **`/dev-tx-getter`**  
     Demonstrates successful access to lazy-fetched fields via getters under a `Transaction`:
     ```html
     DeveloperEntity{id=2, firstName='Bob', lastName='Johnson', startDate=2023-01-01, endDate=2023-12-31, position=DevelopmentPositionEntity{id=2, title='Data Analyst', requiredSkills=[DevelopmentSkillEntity{id=2, skillName='Python'}]}, skills=[DevelopmentSkillEntity{id=2, skillName='Python'}, DevelopmentSkillEntity{id=3, skillName='SQL'}]}
     ```

   - **`/dev-tx-hibernate`**  
     Demonstrates successful access to lazy-fetched fields using `Hibernate.initialize()` under a `Transaction`:
     *(Example response shown earlier)*

   - **`/dev-tx-dto`**  
     Demonstrates converting an entity to a DTO under a `Transaction`:
     *(Example response shown earlier)*

   - **`/json-error`**  
     Demonstrates the failure of serializing lazy-fetched fields wrapped in `HibernateProxy`:
     *(Example response shown earlier)*

   - **`/json-hibernate`**  
     Demonstrates the serialization of lazy-fetched fields using a `HibernateProxyAdapterFactory` with `Hibernate.initialize()`:
     *(Example response shown earlier)*

   - **`/json-dto`**  
     Demonstrates the serialization of DTO created from the entity under a `Transaction`:
     *(Example response shown earlier)*

   - **`/json-tx`**  
     Demonstrates the serialization of entities with `HibernateProxy` fields using a `HibernateProxyAdapterFactory` in an active `Transaction`:
     *(Example response shown earlier)*

---

### Notes:
- All endpoint URLs start with the base path `http://localhost:8081/api/lazy-fetch`.  
- Use the endpoints in sequence to understand the challenges and solutions for handling lazy fetching with JPA and Hibernate.

