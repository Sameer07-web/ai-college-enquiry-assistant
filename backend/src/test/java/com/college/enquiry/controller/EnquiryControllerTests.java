package com.college.enquiry.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EnquiryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/v1/enquiries with valid request should return HTTP 201 and standardized ApiResponse")
    void shouldCreateEnquirySuccessfully() throws Exception {
        String validJson = """
                {
                    "collegeId": "mit-coe",
                    "studentName": "Aditi Sharma",
                    "studentEmail": "aditi.sharma@example.com",
                    "category": "ADMISSIONS",
                    "question": "What are the cutoff ranks for Computer Engineering for the 2026 academic year?"
                }
                """;

        mockMvc.perform(post("/api/v1/enquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", is("Enquiry submitted successfully")))
                .andExpect(jsonPath("$.data.enquiryId", not(emptyOrNullString())))
                .andExpect(jsonPath("$.data.collegeId", is("mit-coe")))
                .andExpect(jsonPath("$.data.studentName", is("Aditi Sharma")))
                .andExpect(jsonPath("$.data.studentEmail", is("aditi.sharma@example.com")))
                .andExpect(jsonPath("$.data.category", is("ADMISSIONS")))
                .andExpect(jsonPath("$.data.status", is("RECEIVED")))
                .andExpect(jsonPath("$.timestamp", not(emptyOrNullString())));
    }

    @Test
    @DisplayName("POST /api/v1/enquiries with invalid fields should return HTTP 400 and validation error details")
    void shouldReturnBadRequestWhenValidationFails() throws Exception {
        String invalidJson = """
                {
                    "collegeId": "INVALID_UPPERCASE",
                    "studentName": "",
                    "studentEmail": "not-an-email",
                    "category": "INVALID_CATEGORY",
                    "question": "Too short"
                }
                """;

        mockMvc.perform(post("/api/v1/enquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("Validation failed for one or more fields")))
                .andExpect(jsonPath("$.data", hasSize(6)))
                .andExpect(jsonPath("$.timestamp", not(emptyOrNullString())));
    }

    @Test
    @DisplayName("POST /api/v1/enquiries with malformed JSON body should return HTTP 400")
    void shouldReturnBadRequestWhenJsonIsMalformed() throws Exception {
        String malformedJson = "{ \"collegeId\": \"mit-coe\", \"studentName\": ";

        mockMvc.perform(post("/api/v1/enquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("Malformed JSON request or invalid request body format")))
                .andExpect(jsonPath("$.timestamp", not(emptyOrNullString())));
    }

    @Test
    @DisplayName("GET /api/v1/enquiries/{id} with nonexistent ID should return HTTP 404")
    void shouldReturnNotFoundForNonExistentEnquiry() throws Exception {
        mockMvc.perform(get("/api/v1/enquiries/00000000-0000-0000-0000-000000000000"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("Enquiry not found with ID: 00000000-0000-0000-0000-000000000000")))
                .andExpect(jsonPath("$.timestamp", not(emptyOrNullString())));
    }

    @Test
    @DisplayName("GET /api/v1/unknown-endpoint should return HTTP 404")
    void shouldReturnNotFoundForUnmappedEndpoint() throws Exception {
        mockMvc.perform(get("/api/v1/unknown-endpoint"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.timestamp", not(emptyOrNullString())));
    }
}
