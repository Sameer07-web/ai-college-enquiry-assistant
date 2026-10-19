package com.college.enquiry.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Request DTO representing a prospective student's college enquiry.
 * Demonstrates Jakarta Bean Validation with realistic college domain fields.
 */
public class EnquiryRequest {

    @NotBlank(message = "College identifier is required")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "College identifier must contain only lowercase alphanumeric characters and hyphens")
    @Size(min = 2, max = 50, message = "College identifier must be between 2 and 50 characters")
    private String collegeId;

    @NotBlank(message = "Student name is required")
    @Size(min = 2, max = 100, message = "Student name must be between 2 and 100 characters")
    private String studentName;

    @NotBlank(message = "Student email is required")
    @Email(message = "Student email must be a well-formed email address")
    private String studentEmail;

    @NotBlank(message = "Category is required")
    @Pattern(regexp = "^(?i)(ADMISSIONS|COURSES|FEES|SCHOLARSHIPS|HOSTEL|GENERAL)$",
            message = "Category must be one of: ADMISSIONS, COURSES, FEES, SCHOLARSHIPS, HOSTEL, GENERAL")
    private String category;

    @NotBlank(message = "Question is required")
    @Size(min = 10, max = 1000, message = "Question must be between 10 and 1000 characters")
    private String question;

    public EnquiryRequest() {
    }

    public EnquiryRequest(String collegeId, String studentName, String studentEmail, String category, String question) {
        this.collegeId = collegeId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.category = category;
        this.question = question;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
