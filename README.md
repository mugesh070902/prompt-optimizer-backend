# AI Prompt Optimizer - Backend

A Spring Boot REST API for analyzing, scoring, and optimizing AI prompts with token-efficient analysis.

## 📋 Project Overview

This backend service provides:
- **Prompt Analysis**: Evaluates prompt quality with heuristic-based scoring
- **Issue Detection**: Identifies missing context, constraints, output formats, etc.
- **Improved Prompts**: Generates better versions using RTCEO format
- **Agent MDGeneration**: Creates structured Markdown files for AI agents
- **History Management**: Stores and retrieves past analyses
- **Pagination Support**: Browse large result sets efficiently

## 🛠 Tech Stack

- **Framework**: Spring Boot 3.2.4
- **Language**: Java 21+
- **Database**: H2 (embedded in-memory for development)
- **ORM**: Spring Data JPA / Hibernate
- **Build**: Maven 3.9+
- **API**: RESTful with JSON endpoints

## 📁 Project Structure

```
prompt-optimizer-backend/
├── src/
│   ├── main/
│   │   ├── java/com/promptoptimizer/
│   │   │   ├── controller/        # REST endpoints
│   │   │   ├── service/           # Business logic
│   │   │   ├── repository/        # Data access
│   │   │   ├── model/             # JPA entities
│   │   │   ├── dto/               # Data transfer objects
│   │   │   ├── exception/         # Error handling
│   │   │   └── PromptOptimizerApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
├── pom.xml                        # Maven configuration
└── target/
    └── prompt-optimizer-backend-1.0.0.jar

```

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.9+

### Installation & Build

1. **Navigate to backend directory**:
   ```bash
   cd prompt-optimizer-backend
   ```

2. **Build the project**:
   ```bash
   mvn clean -DskipTests package
   ```

3. **Run the application**:
   ```bash
   java -jar target/prompt-optimizer-backend-1.0.0.jar
   ```

The backend will start on **`http://localhost:8080`**

## 📡 API Endpoints

### Analyze Prompt
```
POST /api/analyze
Content-Type: application/json

Request:
{
  "prompt": "Explain quantum computing in simple terms"
}

Response:
{
  "id": 1,
  "promptText": "Explain quantum computing in simple terms",
  "analysisScore": 45.0,
  "issues": [
    "No constraints or limitations specified",
    "Missing context - consider specifying audience or use case",
    "No output format specified"
  ],
  "improvedPrompt": "As an Expert Educator, explain quantum computing...",
  "agentMd": "# Role\nAI Prompt Engineer and Software Architect\n...",
  "createdAt": [2026, 4, 7, 15, 36, 20, 871848000]
}
```

### Get History (Paginated)
```
GET /api/history?page=0&size=10

Response:
{
  "content": [ /* prompt objects */ ],
  "totalPages": 1,
  "totalElements": 1,
  "size": 10,
  "number": 0
}
```

### Get Prompt by ID
```
GET /api/history/{id}
```

### Delete Prompt
```
DELETE /api/history/{id}
```

### Get Statistics
```
GET /api/stats

Response:
{
  "totalPrompts": 5
}
```

### Health Check
```
GET /api/health

Response:
{
  "status": "UP"
}
```

## 🗄 Database

The application uses **H2 in-memory database** configured in `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:prompt_optimizer
spring.jpa.hibernate.ddl-auto=update
```

### H2 Console

Access H2 Console at: `http://localhost:8080/h2-console`
- **URL**: `jdbc:h2:mem:prompt_optimizer`
- **Username**: `sa`
- **Password**: (blank)

## 📊 Key Classes

| Class | Purpose |
|-------|---------|
| `PromptController` | REST endpoints |
| `PromptService` | Orchestrates analysis & storage |
| `PromptAnalysisService` | Analyzes prompts & calculates scores |
| `Prompt` | JPA entity for database |
| `PromptRequest` | Input DTO with validation |
| `PromptResponse` | Output DTO |

## ⚙ Configuration

Edit `src/main/resources/application.properties` to customize:

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:h2:mem:prompt_optimizer
spring.jpa.hibernate.ddl-auto=update

# Logging
logging.level.com.promptoptimizer=DEBUG
```

## 🧪 Testing

Run unit tests:
```bash
mvn test
```

Run tests without building JAR:
```bash
mvn clean test
```

## 🔧 Development

### Add Dependencies
Edit `pom.xml` and add dependencies, then run:
```bash
mvn clean install
```

### Code Style
- Use meaningful variable names
- Add Javadoc comments for public methods
- Follow Spring naming conventions

## 🐛 Troubleshooting

### Port Already in Use
```bash
# Find process on port 8080
netstat -ano | findstr :8080

# Kill process (Windows)
taskkill /PID <PID> /F
```

### Database Lock
The in-memory database doesn't persist between sessions. Data is cleared when the application stops.

### Build Failures
```bash
# Clean and rebuild
mvn clean package -DskipTests
```

## 📝 License

This project is part of the AI Prompt Optimizer suite.

## 📧 Support

For issues or questions, refer to the frontend documentation or create an issue in the repository.
