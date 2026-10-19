package com.college.enquiry.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Application-level configuration properties.
 * Maps configuration with the "app" prefix in application.yaml.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name = "AI College Enquiry Assistant";
    private String version = "1.0.0";
    private String description = "AI College Enquiry Assistant - Phase 1 Foundation";
    private String apiPrefix = "/api/v1";

    public AppProperties() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApiPrefix() {
        return apiPrefix;
    }

    public void setApiPrefix(String apiPrefix) {
        this.apiPrefix = apiPrefix;
    }
}
