package com.college.eventregistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationRequestDTO {

    @NotBlank(message = "Participant name is required")
    @Size(min = 2, max = 150, message = "Participant name must be between 2 and 150 characters")
    private String participantName;

    @NotBlank(message = "Participant email is required")
    @Email(message = "Please provide a valid email address (e.g., student@college.edu)")
    @Size(max = 150, message = "Email cannot exceed 150 characters")
    private String participantEmail;

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(String participantName, String participantEmail) {
        this.participantName = participantName;
        this.participantEmail = participantEmail;
    }

    // Getters and Setters
    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }
}
