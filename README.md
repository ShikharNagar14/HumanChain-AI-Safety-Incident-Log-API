**AI Safety Incident Log Service - HumanChain Backend Assignment**
🚀 Project Overview
This is a simple RESTful API built with Java and Spring Boot to manage and log hypothetical AI Safety Incidents.
It provides endpoints to create, retrieve, and delete incidents, with basic validation and error handling.
All incident records are persisted into an SQL database (using MySQL or H2 for local setup).

⚙️ Tech Stack
Language: Java 17

Framework: Spring Boot 3.x

Database: MySQL (or H2 in-memory for easy local testing)

ORM: Spring Data JPA (Hibernate)

🛠️ Setup Instructions
1. Prerequisites
Make sure you have installed:

Java 17 or higher

Maven

MySQL server (OR you can use H2 in-memory database for local testing)

2. Clone the Project
bash
Copy
Edit
git clone https://github.com/your-username/ai-safety-incident-log.git
cd ai-safety-incident-log
3. Configure Database Connection
Inside src/main/resources/application.properties, configure:

properties
Copy
Edit
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
💡 Note:
You must create a database named incidentdb in your MySQL server before running:

sql
Copy
Edit
CREATE DATABASE incidentdb;
4. Build and Run the Application
bash
Copy
Edit
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
The server will start on http://localhost:8080

🗄️ Database Schema
Incident Table Fields:


Field	Type	Description
id	Long (PK)	Auto-generated primary key
title	String	Short summary of the incident
description	Text	Detailed description of the incident
severity	String	Severity level ("Low", "Medium", "High")
reported_at	Timestamp	Auto-filled timestamp when created
✅ Tables will be auto-created by Hibernate (spring.jpa.hibernate.ddl-auto=update), no manual schema setup needed.

📢 API Endpoints

HTTP Method	Endpoint	Description
GET	/incidents	Retrieve all incidents
POST	/incidents	Create a new incident
GET	/incidents/{id}	Retrieve an incident by ID
DELETE	/incidents/{id}	Delete an incident by ID
🔥 Example Usage
1. Get all Incidents
bash
Copy
Edit
curl -X GET http://localhost:8080/incidents
✅ Response:

json
Copy
Edit
[
  {
    "id": 1,
    "title": "AI model generated fake news",
    "description": "A language model generated disinformation during a news report.",
    "severity": "High",
    "reported_at": "2025-04-25T15:30:00Z"
  },
  ...
]
2. Create a New Incident
bash
Copy
Edit
curl -X POST http://localhost:8080/incidents \
  -H "Content-Type: application/json" \
  -d '{
        "title": "Unauthorized AI decision",
        "description": "AI system made unauthorized financial decisions.",
        "severity": "Medium"
      }'
✅ Response:

json
Copy
Edit
{
  "id": 3,
  "title": "Unauthorized AI decision",
  "description": "AI system made unauthorized financial decisions.",
  "severity": "Medium",
  "reported_at": "2025-04-25T16:00:00Z"
}
3. Get Incident by ID
bash
Copy
Edit
curl -X GET http://localhost:8080/incidents/1
✅ Response:

json
Copy
Edit
{
  "id": 1,
  "title": "AI model generated fake news",
  "description": "A language model generated disinformation during a news report.",
  "severity": "High",
  "reported_at": "2025-04-25T15:30:00Z"
}
4. Delete Incident by ID
bash
Copy
Edit
curl -X DELETE http://localhost:8080/incidents/1
✅ Response:

bash
Copy
Edit
(No Content - 204)
If the ID does not exist:

bash
Copy
Edit
Incident not found with ID: 1
📝 Sample Data (Optional)
To quickly populate the database for testing, you can manually POST these sample incidents or insert into DB:

json
Copy
Edit
[
  {
    "title": "Bias in recruitment AI",
    "description": "An AI model unfairly filtered out candidates based on race and gender.",
    "severity": "High"
  },
  {
    "title": "AI surveillance overreach",
    "description": "An AI-powered surveillance system over-collected personal data.",
    "severity": "Medium"
  }
]
⚡ Design Decisions & Challenges
Used Spring Boot for quick API development and cleaner project structure.

Followed RESTful design principles.

Used Spring Data JPA to simplify database operations.

Implemented basic input validation (e.g., severity allowed values).

Used standard HTTP response codes and clear error messages.

Focused on readable, modular, and extensible code.

📬 Contact
If you have any questions, feel free to reach out via email:
your-email@example.com
