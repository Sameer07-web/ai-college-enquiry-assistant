package com.college.enquiry.controller;

import com.college.enquiry.dto.ApiResponse;
import com.college.enquiry.dto.EnquiryRequest;
import com.college.enquiry.dto.EnquiryResponse;
import com.college.enquiry.service.EnquiryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller exposing prospective student enquiry operations.
 * Demonstrates request validation and standardized API response wrapping.
 */
@RestController
@RequestMapping("/api/v1/enquiries")
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    /**
     * Submits a new college enquiry.
     *
     * @param request validated enquiry payload
     * @return ApiResponse containing the created EnquiryResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse<EnquiryResponse>> createEnquiry(
            @Valid @RequestBody EnquiryRequest request) {

        EnquiryResponse response = enquiryService.submitEnquiry(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Enquiry submitted successfully", response));
    }

    /**
     * Retrieves an existing enquiry by its ID.
     *
     * @param id enquiry UUID
     * @return ApiResponse containing the EnquiryResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EnquiryResponse>> getEnquiry(@PathVariable("id") String id) {
        EnquiryResponse response = enquiryService.getEnquiryById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
