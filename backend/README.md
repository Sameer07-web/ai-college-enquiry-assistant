# Backend — Spring Boot API

This directory will contain the Java 21 / Spring Boot REST API.

## Planned packages
- `config/` — application configuration
- `security/` — authentication, authorization and tenant membership checks
- `college/` — college and membership management
- `document/` — upload, validation, extraction and lifecycle
- `rag/` — chunking, embedding, retrieval and answer orchestration
- `chat/` — conversations, messages and citations
- `feedback/` — ratings and issue reports
- `common/` — shared API response and error handling

## Initial setup
Generate a Maven project here using Spring Initializr with Java 21 and a compatible Spring Boot release. Add dependencies deliberately as features are implemented.

Suggested initial dependencies: Spring Web, Spring Data JPA, Spring Security, Validation, MySQL Driver, Actuator, and test dependencies. Add Spring AI and Qdrant integration after checking compatible versions.

Keep secrets in environment variables or an untracked local `.env` file. Never commit credentials.
