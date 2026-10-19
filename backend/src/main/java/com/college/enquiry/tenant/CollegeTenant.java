package com.college.enquiry.tenant;

import java.util.regex.Pattern;

/**
 * Architectural foundation for Multi-College (Multi-Tenant) operations.
 *
 * <p><strong>Design Convention:</strong>
 * College identifiers must be lowercase alphanumeric strings with hyphens, between 2 and 50 characters
 * (e.g., {@code "mit-coe"}, {@code "stanford-eng"}).
 *
 * <p><strong>Multi-College Association Blueprint (Phase 2):</strong>
 * <ul>
 *   <li><strong>Relational Database (MySQL):</strong> Every tenant-scoped entity (e.g., documents, chunks,
 *       conversations, enquiries) will contain a foreign key column {@code college_id VARCHAR(50) NOT NULL}.</li>
 *   <li><strong>Vector Database (Qdrant):</strong> Every indexed chunk payload will store {@code college_id},
 *       and every similarity query must enforce a tenant payload filter.</li>
 *   <li><strong>Security Invariant:</strong> A client-supplied college identifier in an HTTP header, URL path,
 *       or request body is strictly a <em>requested context</em>, NEVER proof of authorization. In Phase 2,
 *       Spring Security authentication and JWT claims will server-validate user membership against the target
 *       college before granting access.</li>
 * </ul>
 */
public final class CollegeTenant {

    public static final String TENANT_HEADER = "X-College-Id";
    private static final Pattern VALID_IDENTIFIER_PATTERN = Pattern.compile("^[a-z0-9-]{2,50}$");

    private CollegeTenant() {
        // Utility / Value convention class
    }

    /**
     * Validates whether a candidate college identifier conforms to the architectural standard.
     *
     * @param identifier the college identifier string
     * @return true if valid, false otherwise
     */
    public static boolean isValid(String identifier) {
        return identifier != null && VALID_IDENTIFIER_PATTERN.matcher(identifier.trim()).matches();
    }

    /**
     * Normalizes a college identifier to lowercase trimmed form.
     *
     * @param identifier raw identifier
     * @return normalized identifier, or null if input is null
     */
    public static String normalize(String identifier) {
        return identifier == null ? null : identifier.trim().toLowerCase();
    }
}
