package com.college.enquiry.dto;

/**
 * Health response DTO for the custom health endpoint /api/v1/health.
 */
public class HealthResponse {

    private String status;
    private String service;
    private String version;

    public HealthResponse() {
    }

    public HealthResponse(String status, String service, String version) {
        this.status = status;
        this.service = service;
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
