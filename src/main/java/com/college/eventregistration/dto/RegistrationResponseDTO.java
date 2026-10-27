package com.college.eventregistration.dto;

import com.college.eventregistration.entity.Registration;
import java.time.LocalDateTime;

public class RegistrationResponseDTO {

    private Long id;
    private String participantName;
    private String participantEmail;
    private Long eventId;
    private String eventName;
    private LocalDateTime registeredAt;

    public RegistrationResponseDTO() {
    }

    public RegistrationResponseDTO(Long id, String participantName, String participantEmail,
                                   Long eventId, String eventName, LocalDateTime registeredAt) {
        this.id = id;
        this.participantName = participantName;
        this.participantEmail = participantEmail;
        this.eventId = eventId;
        this.eventName = eventName;
        this.registeredAt = registeredAt;
    }

    public static RegistrationResponseDTO fromEntity(Registration reg) {
        return new RegistrationResponseDTO(
                reg.getId(),
                reg.getParticipantName(),
                reg.getParticipantEmail(),
                reg.getEvent() != null ? reg.getEvent().getId() : null,
                reg.getEvent() != null ? reg.getEvent().getName() : null,
                reg.getRegisteredAt()
        );
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}
