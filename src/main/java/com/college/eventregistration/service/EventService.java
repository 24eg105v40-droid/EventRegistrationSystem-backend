package com.college.eventregistration.service;

import com.college.eventregistration.dto.DashboardSummaryDTO;
import com.college.eventregistration.dto.EventRequestDTO;
import com.college.eventregistration.dto.EventResponseDTO;

import java.util.List;

public interface EventService {

    List<EventResponseDTO> getAllEvents();

    EventResponseDTO getEventById(Long id);

    EventResponseDTO createEvent(EventRequestDTO requestDTO);

    EventResponseDTO updateEvent(Long id, EventRequestDTO requestDTO);

    void deleteEvent(Long id);

    DashboardSummaryDTO getDashboardSummary();
}
