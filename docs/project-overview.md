# Project Overview

## Title
AI College Enquiry Assistant

## Problem
College information is distributed across handbooks, circulars, fee documents, examination notices, academic calendars, placement documents and policies. Students may struggle to find reliable answers, while staff repeatedly answer similar questions. General-purpose chatbots may provide unsupported answers.

## Proposed solution
A web application using Retrieval-Augmented Generation (RAG) to retrieve relevant passages from approved institutional documents and generate concise answers with source references. If evidence is insufficient, the assistant should state that limitation instead of inventing a policy.

## Users
- Student
- College Administrator

## MVP
- Authentication and role-based access
- College membership and tenant isolation
- PDF upload and text extraction
- Chunking, embeddings and vector retrieval
- Grounded answer generation
- Source citations with page numbers where available
- Insufficient-evidence fallback
- Document management and processing status
- Basic feedback
- Evaluation dataset and security tests

## Constraints
- Team of 3–4
- Less than two months
- Demonstrate with two colleges
- Prefer free-tier AI services or feasible local alternatives
- Start with text-based PDFs
- Use a modular monolith, not microservices
