package com.college.eventregistration.dto;

import com.college.eventregistration.entity.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventResponseDTO {

    private Long id;
    private String name;
    private LocalDate date;
    private String venue;
    private String description;
    private long registrationCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EventResponseDTO() {
    }

    public EventResponseDTO(Long id, String name, LocalDate date, String venue, String description,
                            long registrationCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.description = description;
        this.registrationCount = registrationCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static EventResponseDTO fromEntity(Event event, long count) {
        return new EventResponseDTO(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getVenue(),
                event.getDescription(),
                count,
                event.getCreatedAt(),
                event.getUpdatedAt()
        );
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRegistrationCount() {
        return registrationCount;
    }

    public void setRegistrationCount(long registrationCount) {
        this.registrationCount = registrationCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
