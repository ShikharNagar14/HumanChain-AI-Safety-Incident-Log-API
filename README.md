# AI Safety Incident Log Service - HumanChain Backend Assignment

# Project Overview
This is a simple RESTful API built with Java and Spring Boot to manage and log hypothetical AI Safety Incidents.
It provides endpoints to create, retrieve, and delete incidents, with basic validation and error handling.
All incident records are persisted into an SQL database (using MySQL or H2 for local setup).

# Tech Stack
Language: Java 17

Framework: Spring Boot 3.x

Database: MySQL (or H2 in-memory for easy local testing)

ORM: Spring Data JPA (Hibernate)

# Setup Instructions
1. Prerequisites
Make sure you have installed:

Java 17 or higher

Maven

MySQL server (OR you can use H2 in-memory database for local testing)

2. Clone the Project

git clone https://github.com/ShikharNagar14/ai-safety-incident-log.git

cd ai-safety-incident-log

3. Configure Database Connection

Inside src/main/resources/application.properties, configure:


properties

# For MySQL database
spring.datasource.url=jdbc:mysql://localhost:3306/incidentdb

spring.datasource.username=root

spring.datasource.password=yourpassword

# JPA Properties

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server Port (optional)

server.port=8080

# Note:

You must create a database named incidentdb in your MySQL server before running:

CREATE DATABASE incidentdb;

Field	Type	Description

id	Long (PK)	Auto-generated primary key

title	String	Short summary of the incident

description	Text	Detailed description of the incident

severity	String	Severity level ("Low", "Medium", "High")

reported_at	Timestamp	Auto-filled timestamp when created

Tables will be auto-created by Hibernate (spring.jpa.hibernate.ddl-auto=update), no manual schema setup needed.

# API Endpoints

HTTP Method	Endpoint	Description

GET	/incidents/all	Retrieve all incidents

POST	/incidents/save	Create a new incident

GET	/incidents/{id}	Retrieve an incident by ID

DELETE	/incidents/{id}	Delete an incident by ID

# Example 

1. Get all Incidents

curl -X GET http://localhost:8080/incidents/all


2. Create a New Incident

curl -X POST http://localhost:8080/incidents/save

   {
        "title": "Unauthorized AI decision",
        "description": "AI system made unauthorized financial decisions.",
        "severity": "Medium"
      }
   


3. Get Incident by ID

curl -X GET http://localhost:8080/incidents/1

4. Delete Incident by ID

curl -X DELETE http://localhost:8080/incidents/1

# Design Decisions & Challenges

Used Spring Boot for quick API development and cleaner project structure.

Followed RESTful design principles.

Used Spring Data JPA to simplify database operations.

Implemented basic input validation (e.g., severity allowed values).

Used standard HTTP response codes and clear error messages.

Focused on readable, modular, and extensible code.

# Contact

If you have any questions, feel free to reach out via email:

shikharnagar1@gmail.com
