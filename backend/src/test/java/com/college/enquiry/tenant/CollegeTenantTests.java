package com.college.enquiry.tenant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CollegeTenantTests {

    @ParameterizedTest
    @ValueSource(strings = {"mit-coe", "stanford-eng", "college-123", "dept-a"})
    @DisplayName("Should accept valid college tenant identifiers")
    void shouldAcceptValidTenantIdentifiers(String validId) {
        assertThat(CollegeTenant.isValid(validId)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "Invalid_UnderScore", "space id", "college/slash", "UPPERCASE"})
    @DisplayName("Should reject invalid college tenant identifiers")
    void shouldRejectInvalidTenantIdentifiers(String invalidId) {
        assertThat(CollegeTenant.isValid(invalidId)).isFalse();
    }

    @Test
    @DisplayName("Should normalize college identifier to lowercase trimmed string")
    void shouldNormalizeTenantIdentifier() {
        assertThat(CollegeTenant.normalize("  STANFORD-ENG  ")).isEqualTo("stanford-eng");
        assertThat(CollegeTenant.normalize(null)).isNull();
    }
}
