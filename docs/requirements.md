# Requirements

## Functional requirements
1. Users can authenticate securely.
2. The backend verifies membership in the selected college.
3. Authorized administrators can upload supported PDF documents.
4. The system extracts text and page metadata where available.
5. The system chunks extracted text and creates embeddings.
6. Chunks are indexed in Qdrant with college-scoped metadata.
7. Students can submit natural-language questions.
8. Retrieval is filtered by the authenticated user's authorized college.
9. The assistant answers using retrieved evidence.
10. Responses include source references and excerpts where available.
11. Insufficient evidence results in a transparent fallback.
12. Administrators can view processing status and remove or replace documents.
13. Students can view their permitted conversation history and submit feedback.

## Non-functional requirements
- Tenant isolation is enforced server-side.
- Uploaded files are validated and size-limited.
- Secrets are not committed to source control.
- Errors do not expose secrets or sensitive data.
- Citations can be traced to source chunks.
- The system is testable and documented.

## Out of scope for the first MVP
- Live ERP integrations
- Attendance or payment actions
- Voice interaction
- OCR for scanned PDFs unless essential
- Fine-tuning a model
- Microservices or Kubernetes
