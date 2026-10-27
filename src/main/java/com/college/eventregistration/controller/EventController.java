package com.college.eventregistration.controller;

import com.college.eventregistration.dto.DashboardSummaryDTO;
import com.college.eventregistration.dto.EventRequestDTO;
import com.college.eventregistration.dto.EventResponseDTO;
import com.college.eventregistration.dto.RegistrationRequestDTO;
import com.college.eventregistration.dto.RegistrationResponseDTO;
import com.college.eventregistration.service.EventService;
import com.college.eventregistration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class EventController {

    private final EventService eventService;
    private final RegistrationService registrationService;

    public EventController(EventService eventService, RegistrationService registrationService) {
        this.eventService = eventService;
        this.registrationService = registrationService;
    }

    /**
     * Retrieve all events with their registration counts
     */
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    /**
     * Retrieve high-level dashboard summary statistics
     */
    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDTO> getDashboardSummary() {
        return ResponseEntity.ok(eventService.getDashboardSummary());
    }

    /**
     * Retrieve a specific event by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    /**
     * Create a new college event
     */
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventRequestDTO requestDTO) {
        EventResponseDTO created = eventService.createEvent(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Update an existing event
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequestDTO requestDTO) {
        EventResponseDTO updated = eventService.updateEvent(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete an event by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Event successfully deleted."));
    }

    /**
     * Retrieve all participants registered for an event
     */
    @GetMapping("/{eventId}/registrations")
    public ResponseEntity<List<RegistrationResponseDTO>> getRegistrationsForEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(registrationService.getRegistrationsByEventId(eventId));
    }

    /**
     * Register a student participant for an event
     */
    @PostMapping("/{eventId}/registrations")
    public ResponseEntity<RegistrationResponseDTO> registerParticipant(
            @PathVariable Long eventId,
            @Valid @RequestBody RegistrationRequestDTO requestDTO) {
        RegistrationResponseDTO registration = registrationService.registerParticipant(eventId, requestDTO);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

    /**
     * Retrieve the registration count for an event
     */
    @GetMapping("/{eventId}/registrations/count")
    public ResponseEntity<Map<String, Object>> getRegistrationCount(@PathVariable Long eventId) {
        long count = registrationService.getRegistrationCountByEventId(eventId);
        Map<String, Object> response = Map.of(
                "eventId", eventId,
                "count", count
        );
        return ResponseEntity.ok(response);
    }
}
