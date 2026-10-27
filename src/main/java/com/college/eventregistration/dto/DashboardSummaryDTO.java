package com.college.eventregistration.dto;

import java.util.List;

public class DashboardSummaryDTO {

    private long totalEvents;
    private long totalRegistrations;
    private long eventsWithNoRegistrations;
    private String mostPopularEvent;
    private long mostPopularEventCount;
    private List<EventResponseDTO> events;

    public DashboardSummaryDTO() {
    }

    public DashboardSummaryDTO(long totalEvents, long totalRegistrations, long eventsWithNoRegistrations,
                               String mostPopularEvent, long mostPopularEventCount, List<EventResponseDTO> events) {
        this.totalEvents = totalEvents;
        this.totalRegistrations = totalRegistrations;
        this.eventsWithNoRegistrations = eventsWithNoRegistrations;
        this.mostPopularEvent = mostPopularEvent;
        this.mostPopularEventCount = mostPopularEventCount;
        this.events = events;
    }

    // Getters and Setters
    public long getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(long totalEvents) {
        this.totalEvents = totalEvents;
    }

    public long getTotalRegistrations() {
        return totalRegistrations;
    }

    public void setTotalRegistrations(long totalRegistrations) {
        this.totalRegistrations = totalRegistrations;
    }

    public long getEventsWithNoRegistrations() {
        return eventsWithNoRegistrations;
    }

    public void setEventsWithNoRegistrations(long eventsWithNoRegistrations) {
        this.eventsWithNoRegistrations = eventsWithNoRegistrations;
    }

    public String getMostPopularEvent() {
        return mostPopularEvent;
    }

    public void setMostPopularEvent(String mostPopularEvent) {
        this.mostPopularEvent = mostPopularEvent;
    }

    public long getMostPopularEventCount() {
        return mostPopularEventCount;
    }

    public void setMostPopularEventCount(long mostPopularEventCount) {
        this.mostPopularEventCount = mostPopularEventCount;
    }

    public List<EventResponseDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventResponseDTO> events) {
        this.events = events;
    }
}
