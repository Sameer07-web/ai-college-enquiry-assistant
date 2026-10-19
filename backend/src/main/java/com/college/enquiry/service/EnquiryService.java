package com.college.enquiry.service;

import com.college.enquiry.dto.EnquiryRequest;
import com.college.enquiry.dto.EnquiryResponse;
import com.college.enquiry.exception.ResourceNotFoundException;
import com.college.enquiry.tenant.CollegeTenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service managing college enquiries.
 * In Phase 1, enquiries are held in an in-memory repository to validate workflows
 * without requiring database dependencies.
 */
@Service
public class EnquiryService {

    private static final Logger log = LoggerFactory.getLogger(EnquiryService.class);

    private final Map<String, EnquiryResponse> enquiryStore = new ConcurrentHashMap<>();

    /**
     * Submits and validates a prospective student enquiry.
     *
     * @param request the validated enquiry request
     * @return EnquiryResponse confirmation
     */
    public EnquiryResponse submitEnquiry(EnquiryRequest request) {
        String normalizedCollegeId = CollegeTenant.normalize(request.getCollegeId());
        String enquiryId = UUID.randomUUID().toString();

        EnquiryResponse response = new EnquiryResponse(
                enquiryId,
                normalizedCollegeId,
                request.getStudentName(),
                request.getStudentEmail(),
                request.getCategory().toUpperCase(),
                request.getQuestion(),
                "RECEIVED",
                Instant.now()
        );

        enquiryStore.put(enquiryId, response);
        log.info("Enquiry submitted successfully: id={}, collegeId={}, category={}",
                enquiryId, normalizedCollegeId, response.getCategory());

        return response;
    }

    /**
     * Retrieves an enquiry by its unique ID.
     *
     * @param enquiryId the enquiry UUID
     * @return EnquiryResponse
     * @throws ResourceNotFoundException if enquiry does not exist
     */
    public EnquiryResponse getEnquiryById(String enquiryId) {
        EnquiryResponse response = enquiryStore.get(enquiryId);
        if (response == null) {
            log.warn("Enquiry lookup failed - resource not found: id={}", enquiryId);
            throw new ResourceNotFoundException("Enquiry not found with ID: " + enquiryId);
        }
        return response;
    }
}
