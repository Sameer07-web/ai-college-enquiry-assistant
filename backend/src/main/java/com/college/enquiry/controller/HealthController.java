package com.college.enquiry.controller;

import com.college.enquiry.config.AppProperties;
import com.college.enquiry.dto.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Custom application health controller.
 * Exposes GET /api/v1/health for external consumers and load balancers.
 */
@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    private final AppProperties appProperties;

    public HealthController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping
    public ResponseEntity<HealthResponse> getHealth() {
        HealthResponse response = new HealthResponse(
                "UP",
                appProperties.getName(),
                appProperties.getVersion()
        );
        return ResponseEntity.ok(response);
    }
}
