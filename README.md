# AI College Enquiry Assistant

A multi-tenant, Retrieval-Augmented Generation (RAG) application that answers college enquiries using approved institutional documents and provides source citations.

> **Status:** Phase 1 — Backend Foundation implemented and verified. Later phase features (MySQL, Spring Security, RAG, Frontend) are planned as documented.

## Goals
- Answer natural-language questions using approved college documents.
- Retrieve relevant passages using embeddings and vector search.
- Generate evidence-grounded answers with source citations.
- Transparently decline to answer when evidence is insufficient.
- Support multiple colleges with server-enforced tenant isolation.
- Allow authorized administrators to manage documents.

## Planned stack

| Layer | Technology |
|---|---|
| Frontend | React, TypeScript, Vite, Tailwind CSS |
| Backend | Java 21, Spring Boot, Maven, Spring AI |
| Relational database | MySQL |
| Vector database | Qdrant |
| PDF extraction | Apache PDFBox |
| Local development | Docker Compose |
| Testing | JUnit 5, Mockito, frontend tests |

## Repository layout
- `frontend/` — React web application
- `backend/` — Spring Boot REST API
- `docs/` — requirements, architecture, database design and API outline
- `deployment/` — local development services
- `sample-data/` — synthetic, non-confidential demo data only
- `.github/` — workflow notes and future CI

## Getting started
The repository currently contains the initial scaffold. Frontend and backend applications will be added incrementally.

### Prerequisites
- Git
- Java 21
- Node.js LTS and npm
- Docker Desktop with Docker Compose
- IntelliJ IDEA or VS Code

### Start local infrastructure
Copy `.env.example` to `.env`, change the local passwords, then run:

```bash
docker compose -f deployment/docker-compose.yml up -d
```

### Application setup
See [`backend/README.md`](backend/README.md) and [`frontend/README.md`](frontend/README.md).

## Security and privacy
- Never commit API keys, passwords, tokens, `.env` files, or production data.
- Do not publish confidential college documents or personal student records.
- Derive tenant authorization from server-validated membership. A client-provided college ID is not proof of access.
- Use synthetic documents for public demonstrations.

## Team
Add confirmed team members and guide details after the team agrees to publish them.

## Development phases
1. Repository foundation and requirements
2. Spring Boot and MySQL foundation
3. Document ingestion and PDF extraction
4. Chunking, embeddings and Qdrant retrieval
5. Grounded answers and citations
6. React chat and admin interfaces
7. Tenant isolation and evaluation
8. Testing, deployment and documentation

## License
No license has been selected yet. Until one is added, do not assume others have permission to reuse this project's code.
