# UniGuide Backend (`uniguide-backend`)

The backend REST service for the UniGuide platform, built with Java, Spring Boot, Maven, and PostgreSQL.

## Phase 1 Overview

Phase 1 establishes the foundational Spring Boot application structure, dependency configurations, PostgreSQL environment variable integration, and basic health probe.

- **Group ID**: `com.uniguide`
- **Artifact ID**: `uniguide-backend`
- **Base Package**: `com.uniguide`
- **Server Port**: `8080`

## Technology Stack

- **Java**: 21
- **Spring Boot**: 3.4.3
- **Build Tool**: Maven
- **Dependencies**:
  - Spring Web (`spring-boot-starter-web`)
  - Spring Data JPA (`spring-boot-starter-data-jpa`)
  - PostgreSQL Driver (`org.postgresql:postgresql`)
  - Validation (`spring-boot-starter-validation`)
  - Project Lombok (`org.projectlombok:lombok`)
  - Spring Boot Test (`spring-boot-starter-test`)

## Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/uniguide/
│   │   │   ├── UniGuideApplication.java
│   │   │   ├── controller/
│   │   │   │   └── HealthController.java
│   │   │   └── dto/
│   │   │       └── HealthResponse.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/uniguide/
│           ├── UniGuideApplicationTests.java
│           └── controller/
│               └── HealthControllerTest.java
├── pom.xml
├── .gitignore
└── README.md
```

## Database Configuration

PostgreSQL connection properties are managed dynamically through environment variables:

| Variable | Description | Default Value |
|---|---|---|
| `DB_URL` | PostgreSQL JDBC connection URL | `jdbc:postgresql://localhost:5432/uniguide` |
| `DB_USERNAME` | Database username | `postgres` |
| `DB_PASSWORD` | Database password | *(empty / set via env)* |

> **Note**: Passwords are never hardcoded. Set `DB_PASSWORD` in your local environment or deployment secrets.

## API Endpoints

### Health Check

- **Method**: `GET`
- **Path**: `/api/health`
- **Response**: `200 OK`
```json
{
  "status": "UP",
  "message": "UniGuide Backend is running"
}
```

## Running & Testing

### Run Tests
```bash
mvn clean test
```

### Run Application
```bash
mvn spring-boot:run
```

Verify health endpoint:
```bash
curl http://localhost:8080/api/health
```
