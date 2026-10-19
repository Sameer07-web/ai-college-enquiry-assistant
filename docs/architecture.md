# Proposed Architecture

## Components
- **Frontend:** React + TypeScript.
- **API:** Java 21 and Spring Boot modular monolith.
- **Relational store:** MySQL for users, memberships, document metadata, chunks, conversations, messages, citations and feedback.
- **Vector store:** Qdrant for embeddings and searchable payload metadata.
- **File storage:** controlled storage for original uploaded files.
- **AI services:** configurable embedding and chat models selected according to free-tier limits or local hardware.

## Document ingestion flow
Administrator authentication and authorization → upload validation → file storage → text extraction → chunking → embedding generation → Qdrant indexing. Store document metadata and chunk records in MySQL.

## Question-answering flow
Student question → authenticated API → validate college membership → embed question → tenant-filtered Qdrant search → evidence sufficiency check → grounded prompt → LLM response → construct citations from retrieved records → save and return answer.

## Security invariant
A client-provided college identifier is only a requested context, never proof of authorization. The backend must validate membership and apply the authorized tenant filter to every relevant SQL and vector query.
