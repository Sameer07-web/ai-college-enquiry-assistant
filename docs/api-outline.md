# Initial REST API Outline

These endpoints are proposed; they are not implemented yet.

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/auth/login` | Authenticate user |
| GET | `/api/colleges` | List colleges current user may access |
| POST | `/api/admin/documents` | Upload a document |
| GET | `/api/admin/documents` | List authorized college documents |
| DELETE | `/api/admin/documents/{id}` | Remove document and index |
| POST | `/api/chat/ask` | Ask a question and receive answer with citations |
| GET | `/api/chat/conversations` | List current user's conversations |
| GET | `/api/chat/conversations/{id}` | Read an authorized conversation |
| POST | `/api/chat/messages/{id}/feedback` | Submit answer feedback |

All protected endpoints must enforce authentication, role permissions, ownership and tenant scope on the server.
