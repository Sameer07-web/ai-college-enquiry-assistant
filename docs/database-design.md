# Proposed Database Design

## MySQL tables
- `colleges`: tenant records.
- `users`: user identities and password hashes or external identity references.
- `college_memberships`: user-to-college membership and role.
- `documents`: college-owned document metadata, version, content hash and processing status.
- `document_chunks`: chunk text, document reference, college identifier, page number, chunk index and Qdrant point ID.
- `conversations`: user and college scope.
- `messages`: conversation messages and response status.
- `message_citations`: mapping between an answer message and supporting chunk records.
- `feedback`: user ratings and comments for messages.

## Qdrant
Store vectors and payload metadata required for filtering, including the college identifier and source/document identifiers. Apply tenant filters on every search.

## File storage
Store original documents outside relational tables; reference them by a controlled storage key.

## Integrity and lifecycle
Use foreign keys, indexes and appropriate unique constraints. On replacement or deletion, ensure obsolete chunks and vector points are deactivated or removed so outdated content cannot be retrieved.
