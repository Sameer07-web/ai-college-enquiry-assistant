# Backend — Spring Boot REST API

Production-grade Spring Boot 4 / Java 21 backend foundation for the **AI College Enquiry Assistant**.

## 1. Phase 1 — Backend Foundation Scope

Phase 1 establishes the production-grade foundation of the service:
- **Clean Package Architecture:** Organized under root package `com.college.enquiry`.
- **Custom Health Endpoint:** `GET /api/v1/health` returning JSON service status and version.
- **Actuator Health Endpoint:** `GET /actuator/health` exposing standard Spring Boot Actuator health checks.
- **Standardized API Response Wrapper:** `ApiResponse<T>` providing uniform `success`, `message`, `data`, and `timestamp` payloads.
- **Centralized Exception Handling:** `@RestControllerAdvice` capturing validation errors, malformed JSON, and resource-not-found events with consistent JSON and zero stack trace leakage.
- **Jakarta Bean Validation:** Demonstrated with realistic college enquiry models.
- **Multi-College Foundation:** Architectural conventions for tenant identifiers and data scoping rules.
- **Deterministic Automated Testing:** 100% passing test suite across context startup, controller interactions, validation, error formats, and serialization.

### Current Limitations (Explicitly Deferred to Phase 2+)
- **No Database Configured:** Currently uses an in-memory concurrent store for demonstration. MySQL integration and schema migrations are scheduled for Phase 2.
- **No Authentication/JWT:** No security filters or login endpoints yet. Client-provided college identifiers are treated solely as requested context, not authorized proof of identity.
- **No Document Ingestion / Vector Search:** Apache PDFBox, embeddings, and Qdrant retrieval are scheduled for Phase 3 & 4.
- **No LLM Integration:** Spring AI chat orchestration belongs to Phase 5.

---

## 2. Technology Stack & Prerequisites

- **Java Development Kit (JDK):** Version 21 (Eclipse Temurin / OpenJDK 21)
- **Framework:** Spring Boot 4.1.1 (Spring Framework 7)
- **Build Tool:** Apache Maven Wrapper (`./mvnw` / `.\mvnw.cmd`)
- **Key Modules:** Spring Web (MVC), Spring Boot Actuator, Spring Boot Validation (Hibernate Validator 9 / Jakarta Validation 3)
- **Testing:** JUnit Jupiter 6, Mockito 5, AssertJ, Spring WebMvc Test / MockMvc

---

## 3. Package Structure

The backend source code is structured under `com.college.enquiry`:

```
src/main/java/com/college/enquiry/
├── CollegeEnquiryAssistantApplication.java    # Application entry point & startup event logging
├── config/
│   └── AppProperties.java                     # Strongly-typed configuration properties (prefix: "app")
├── controller/
│   ├── HealthController.java                  # Custom health endpoint (GET /api/v1/health)
│   └── EnquiryController.java                 # Enquiry submission & lookup demonstration
├── dto/
│   ├── ApiResponse.java                       # Generic standardized API response wrapper
│   ├── HealthResponse.java                    # Custom health response DTO
│   ├── EnquiryRequest.java                    # Jakarta-validated enquiry request DTO
│   ├── EnquiryResponse.java                   # Enquiry confirmation response DTO
│   └── ValidationErrorDetail.java             # Field-level validation error details DTO
├── exception/
│   ├── GlobalExceptionHandler.java            # Centralized @RestControllerAdvice exception handler
│   └── ResourceNotFoundException.java         # 404 Not Found domain exception
├── service/
│   └── EnquiryService.java                    # Enquiry business logic & in-memory store
└── tenant/
    └── CollegeTenant.java                     # Multi-college identifier convention & security invariants
```

---

## 4. Building and Running

All commands should be executed from the `backend/` directory using the Maven Wrapper.

### Compile and Run Tests
```powershell
.\mvnw.cmd clean test
```

### Build Executable Package
```powershell
.\mvnw.cmd package
```
The packaged runnable jar is created at `target/college-enquiry-assistant-0.0.1-SNAPSHOT.jar`.

### Start the Application
Run the packaged JAR:
```powershell
java -jar target/college-enquiry-assistant-0.0.1-SNAPSHOT.jar
```
Or start via Maven Wrapper:
```powershell
.\mvnw.cmd spring-boot:run
```

The application runs on port `8080` by default (configured explicitly in `src/main/resources/application.yaml`).

---

## 5. API Endpoints

### A. Health Endpoints

#### 1. Custom Health Endpoint
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/v1/health`
- **Status:** `200 OK`
- **Response Format:**
```json
{
  "status": "UP",
  "service": "AI College Enquiry Assistant",
  "version": "1.0.0"
}
```

#### 2. Spring Boot Actuator Health
- **Method:** `GET`
- **URL:** `http://localhost:8080/actuator/health`
- **Status:** `200 OK`
- **Response Format:**
```json
{
  "status": "UP",
  "groups": [
    "liveness",
    "readiness"
  ]
}
```

---

### B. Business Enquiry Endpoints (Validation & Error Handling Demonstration)

#### 1. Submit an Enquiry
- **Method:** `POST`
- **URL:** `http://localhost:8080/api/v1/enquiries`
- **Content-Type:** `application/json`

**Valid Request Example:**
```json
{
  "collegeId": "mit-coe",
  "studentName": "Rahul Verma",
  "studentEmail": "rahul.verma@example.com",
  "category": "ADMISSIONS",
  "question": "What are the eligibility criteria and key dates for Computer Science 2026?"
}
```

**Success Response (`201 Created`):**
```json
{
  "success": true,
  "message": "Enquiry submitted successfully",
  "data": {
    "enquiryId": "6c3a697e-884f-4a9a-96a0-562c2216216e",
    "collegeId": "mit-coe",
    "studentName": "Rahul Verma",
    "studentEmail": "rahul.verma@example.com",
    "category": "ADMISSIONS",
    "question": "What are the eligibility criteria and key dates for Computer Science 2026?",
    "status": "RECEIVED",
    "receivedAt": "2026-10-19T10:40:29.199756900Z"
  },
  "timestamp": "2026-10-19T10:40:29.201748100Z"
}
```

#### 2. Validation Error Handling
When invalid fields are submitted (e.g., uppercase college ID, empty name, invalid email, unknown category, or short question):

**Response (`400 Bad Request`):**
```json
{
  "success": false,
  "message": "Validation failed for one or more fields",
  "data": [
    {
      "field": "collegeId",
      "message": "College identifier must contain only lowercase alphanumeric characters and hyphens",
      "rejectedValue": "UPPER"
    },
    {
      "field": "studentName",
      "message": "Student name must be between 2 and 100 characters",
      "rejectedValue": ""
    },
    {
      "field": "studentName",
      "message": "Student name is required",
      "rejectedValue": ""
    },
    {
      "field": "question",
      "message": "Question must be between 10 and 1000 characters",
      "rejectedValue": "short"
    },
    {
      "field": "studentEmail",
      "message": "Student email must be a well-formed email address",
      "rejectedValue": "bad"
    },
    {
      "field": "category",
      "message": "Category must be one of: ADMISSIONS, COURSES, FEES, SCHOLARSHIPS, HOSTEL, GENERAL",
      "rejectedValue": "UNKNOWN"
    }
  ],
  "timestamp": "2026-10-19T10:41:05.734885300Z"
}
```

#### 3. Malformed JSON Request Handling
When malformed or unparseable JSON is sent:

**Response (`400 Bad Request`):**
```json
{
  "success": false,
  "message": "Malformed JSON request or invalid request body format",
  "data": null,
  "timestamp": "2026-10-19T10:42:12.506817200Z"
}
```

#### 4. Retrieve Enquiry by ID
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/v1/enquiries/{id}`

**Success Response (`200 OK`):**
```json
{
  "success": true,
  "message": "Request completed successfully",
  "data": {
    "enquiryId": "6c3a697e-884f-4a9a-96a0-562c2216216e",
    "collegeId": "mit-coe",
    "studentName": "Rahul Verma",
    "studentEmail": "rahul.verma@example.com",
    "category": "ADMISSIONS",
    "question": "What are the eligibility criteria and key dates for Computer Science 2026?",
    "status": "RECEIVED",
    "receivedAt": "2026-10-19T10:40:29.199756900Z"
  },
  "timestamp": "2026-10-19T10:40:29.242781400Z"
}
```

**Non-Existent ID Response (`404 Not Found`):**
```json
{
  "success": false,
  "message": "Enquiry not found with ID: 00000000-0000-0000-0000-000000000000",
  "data": null,
  "timestamp": "2026-10-19T10:42:02.815515800Z"
}
```

---

## 6. Multi-College Architecture Foundation

The platform will serve multiple colleges concurrently. In Phase 1, the architectural foundation and conventions are defined:

1. **Identifier Standard:** Lowercase alphanumeric characters separated by hyphens (e.g., `mit-coe`, `stanford-eng`), validated via `CollegeTenant.isValid()`.
2. **Database Segregation Blueprint (Phase 2):**
   - Every tenant-scoped entity (`documents`, `document_chunks`, `conversations`, `enquiries`) will have a foreign key column `college_id VARCHAR(50) NOT NULL` referencing the `colleges` master table.
   - All SQL queries will enforce college scoping via repository methods and JPA/Hibernate filters.
3. **Vector Database Isolation (Phase 4):**
   - In Qdrant, point payloads will include `college_id`.
   - Every vector similarity query will strictly mandate an exact-match filter `{ "key": "college_id", "match": { "value": "<college_id>" } }`.
4. **Security Invariant:**
   - Client-provided identifiers in HTTP headers, paths, or JSON bodies represent **requested context only**, never authorization.
   - Server-side Spring Security + JWT verification will validate tenant membership before any queries are executed.

---

## 7. Next Steps (Phase 2)
- Relational database schema design and MySQL Flyway/Liquibase migrations.
- Spring Data JPA entities and repositories.
- Spring Security configuration with JWT-based tenant membership authorization.
