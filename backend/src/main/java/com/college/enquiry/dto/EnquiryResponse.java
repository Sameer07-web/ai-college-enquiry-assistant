package com.college.enquiry.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

/**
 * Response DTO representing an enquiry submission confirmation.
 */
public class EnquiryResponse {

    private String enquiryId;
    private String collegeId;
    private String studentName;
    private String studentEmail;
    private String category;
    private String question;
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant receivedAt;

    public EnquiryResponse() {
    }

    public EnquiryResponse(String enquiryId, String collegeId, String studentName, String studentEmail,
                           String category, String question, String status, Instant receivedAt) {
        this.enquiryId = enquiryId;
        this.collegeId = collegeId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.category = category;
        this.question = question;
        this.status = status;
        this.receivedAt = receivedAt;
    }

    public String getEnquiryId() {
        return enquiryId;
    }

    public void setEnquiryId(String enquiryId) {
        this.enquiryId = enquiryId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Instant receivedAt) {
        this.receivedAt = receivedAt;
    }
}
