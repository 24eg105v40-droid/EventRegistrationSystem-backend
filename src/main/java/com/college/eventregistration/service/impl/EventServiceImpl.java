package com.college.eventregistration.service.impl;

import com.college.eventregistration.dto.DashboardSummaryDTO;
import com.college.eventregistration.dto.EventRequestDTO;
import com.college.eventregistration.dto.EventResponseDTO;
import com.college.eventregistration.entity.Event;
import com.college.eventregistration.exception.ResourceNotFoundException;
import com.college.eventregistration.repository.EventRepository;
import com.college.eventregistration.repository.RegistrationRepository;
import com.college.eventregistration.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public EventServiceImpl(EventRepository eventRepository, RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponseDTO> getAllEvents() {
        List<Event> events = eventRepository.findAllByOrderByDateAsc();
        List<EventResponseDTO> responseDTOs = new ArrayList<>();
        for (Event event : events) {
            long count = registrationRepository.countByEventId(event.getId());
            responseDTOs.add(EventResponseDTO.fromEntity(event, count));
        }
        return responseDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponseDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));
        long count = registrationRepository.countByEventId(id);
        return EventResponseDTO.fromEntity(event, count);
    }

    @Override
    public EventResponseDTO createEvent(EventRequestDTO requestDTO) {
        Event event = new Event(
                requestDTO.getName().trim(),
                requestDTO.getDate(),
                requestDTO.getVenue().trim(),
                requestDTO.getDescription().trim()
        );
        Event saved = eventRepository.save(event);
        return EventResponseDTO.fromEntity(saved, 0L);
    }

    @Override
    public EventResponseDTO updateEvent(Long id, EventRequestDTO requestDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));

        event.setName(requestDTO.getName().trim());
        event.setDate(requestDTO.getDate());
        event.setVenue(requestDTO.getVenue().trim());
        event.setDescription(requestDTO.getDescription().trim());

        Event updated = eventRepository.save(event);
        long count = registrationRepository.countByEventId(id);
        return EventResponseDTO.fromEntity(updated, count);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));
        eventRepository.delete(event);
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardSummaryDTO getDashboardSummary() {
        List<EventResponseDTO> events = getAllEvents();
        long totalEvents = events.size();
        long totalRegistrations = 0;
        long zeroRegistrationCount = 0;
        String mostPopularEvent = "None";
        long mostPopularCount = 0;

        for (EventResponseDTO ev : events) {
            long count = ev.getRegistrationCount();
            totalRegistrations += count;
            if (count == 0) {
                zeroRegistrationCount++;
            }
            if (count > mostPopularCount) {
                mostPopularCount = count;
                mostPopularEvent = ev.getName();
            }
        }

        return new DashboardSummaryDTO(
                totalEvents,
                totalRegistrations,
                zeroRegistrationCount,
                mostPopularEvent,
                mostPopularCount,
                events
        );
    }
}
