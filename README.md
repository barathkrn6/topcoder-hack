# topcoder-hack

# Assusming below things have already installed on the given laptop

	1. Docker
	2. Gradle
	3. Java 8


# Database setup on Docker

Step 1 : Pull postgres docker
	
	docker pull postgres

Step 2 : Run postgres docker with database name, username and password configuration
	
	docker run --name db_docker -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres:latest

Step 3 : Get into the docker to run below database script
	
	docker exec -it db_docker /bin/sh

Step 4 : Get into postgres sql inside docker to run below database script
	
	psql -h localhost -U postgres postgres

Step 5 : Run the below sql queries

	  CREATE TABLE users (
	      id INT GENERATED ALWAYS AS IDENTITY,
	      PRIMARY KEY(id),
	      first_name VARCHAR(100) NOT NULL UNIQUE,
	      last_name VARCHAR(100) NOT NULL UNIQUE,
	      email VARCHAR(100) NULL UNIQUE,
	      phone VARCHAR(100) NULL UNIQUE,
	      created_at TIMESTAMP NOT NULL,
	      updated_at TIMESTAMP NOT NULL
	  );

  
Step 5 : Preparing sample data

	  INSERT INTO users(first_name, last_name, email, phone, created_at, updated_at) VALUES ('fname1', 'lname1', 'email1', 'phone1', now(), now());
	  INSERT INTO users(first_name, last_name, email, phone, created_at, updated_at) VALUES ('fname2', 'lname2', 'email2', 'phone2', now(), now());
	  INSERT INTO users(first_name, last_name, email, phone, created_at, updated_at) VALUES ('fname3', 'lname3', 'email3', 'phone3', now(), now());
	  INSERT INTO users(first_name, last_name, email, phone, created_at, updated_at) VALUES ('fname4', 'lname4', 'email4', 'phone4', now(), now());
	  INSERT INTO users(first_name, last_name, email, phone, created_at, updated_at) VALUES ('fname5', 'lname5', 'email5', 'phone5', now(), now());
	  INSERT INTO users(first_name, last_name, email, phone, created_at, updated_at) VALUES ('fname6', 'lname6', 'email6', 'phone6', now(), now());
	  INSERT INTO users(first_name, last_name, email, phone, created_at, updated_at) VALUES ('fname7', 'lname7', 'email7', 'phone7', now(), now());
	  INSERT INTO users(first_name, last_name, email, phone, created_at, updated_at) VALUES ('fname8', 'lname8', 'email8', 'phone8', now(), now());

# Application setup

Clone the spring boot application and run the below commands (Run these commands from spring boot application root directory)

Step 1 : Build the application to generate the jar file
	
	mvn clean install

Step 2 : Create a docker Image for spring boot application
	
	docker build . -t tc-spring-boot

Step 3 : Run the spring boot application by linked database docker
	
	docker run -p 8080:8080 --name tc-spring-boot --link db_docker:postgres -d tc-spring-boot:latest

# Swagger URL

	http://{host_name/ip_addr}:8086/swagger-ui.html

# Sample REQUEST for /search-users API

	http://{host_name/ip_addr}:8086/search-users?query=firstName:fname1
	http://{host_name/ip_addr}:8086/search-users?query=firstName:fname1,lastName:lname1