package com.college.enquiry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CollegeEnquiryAssistantApplication {

	private static final Logger log = LoggerFactory.getLogger(CollegeEnquiryAssistantApplication.class);

	private final Environment environment;

	public CollegeEnquiryAssistantApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(CollegeEnquiryAssistantApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		String port = environment.getProperty("server.port", "8080");
		String appName = environment.getProperty("spring.application.name", "college-enquiry-assistant");
		log.info("Application '{}' successfully started on port {}", appName, port);
		log.info("Health endpoint: http://localhost:{}/api/v1/health", port);
		log.info("Actuator health: http://localhost:{}/actuator/health", port);
	}
}
