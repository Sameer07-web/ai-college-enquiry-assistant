# RAG Evaluation Plan

Build an evaluation set from approved, non-confidential documents before making quality claims.

Suggested initial set: about 50 questions:
- 25 directly answerable questions
- 10 questions requiring multiple passages
- 5 questions whose answers are absent
- 5 ambiguous or conflicting-document questions
- 5 cross-college isolation tests

Track:
- Recall@5 for retrieval
- Answer correctness against approved sources
- Citation correctness
- Appropriate abstention on unsupported questions
- Tenant-isolation failures
- End-to-end latency

Report actual measurements only after running tests. Treat any unauthorized cross-tenant retrieval as a security defect, not as an acceptable average score.
