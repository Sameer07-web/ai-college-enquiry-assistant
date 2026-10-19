package com.college.enquiry.dto;

import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseSerializationTests {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Should correctly serialize standardized ApiResponse with success data")
    void shouldSerializeSuccessResponse() throws Exception {
        Instant fixedInstant = Instant.parse("2026-10-19T10:00:00Z");
        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                true,
                "Request completed successfully",
                Map.of("status", "UP"),
                fixedInstant
        );

        String json = objectMapper.writeValueAsString(response);

        assertThat(json).contains("\"success\":true");
        assertThat(json).contains("\"message\":\"Request completed successfully\"");
        assertThat(json).contains("\"data\":{\"status\":\"UP\"}");
        assertThat(json).contains("\"timestamp\":\"2026-10-19T10:00:00Z\"");
    }

    @Test
    @DisplayName("Should correctly serialize standardized ApiResponse with error and null data")
    void shouldSerializeErrorResponse() throws Exception {
        ApiResponse<Void> response = ApiResponse.error("Something went wrong");

        String json = objectMapper.writeValueAsString(response);

        assertThat(json).contains("\"success\":false");
        assertThat(json).contains("\"message\":\"Something went wrong\"");
        assertThat(json).contains("\"timestamp\":");
    }
}
