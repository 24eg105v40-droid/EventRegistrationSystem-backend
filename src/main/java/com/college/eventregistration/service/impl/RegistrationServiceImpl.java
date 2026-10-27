package com.college.eventregistration.service.impl;

import com.college.eventregistration.dto.RegistrationRequestDTO;
import com.college.eventregistration.dto.RegistrationResponseDTO;
import com.college.eventregistration.entity.Event;
import com.college.eventregistration.entity.Registration;
import com.college.eventregistration.exception.DuplicateRegistrationException;
import com.college.eventregistration.exception.ResourceNotFoundException;
import com.college.eventregistration.repository.EventRepository;
import com.college.eventregistration.repository.RegistrationRepository;
import com.college.eventregistration.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(EventRepository eventRepository, RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistrationResponseDTO> getRegistrationsByEventId(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResourceNotFoundException("Event", "id", eventId);
        }

        return registrationRepository.findByEventIdOrderByRegisteredAtDesc(eventId)
                .stream()
                .map(RegistrationResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationResponseDTO registerParticipant(Long eventId, RegistrationRequestDTO requestDTO) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));

        String email = requestDTO.getParticipantEmail().trim().toLowerCase();
        if (registrationRepository.existsByEventIdAndParticipantEmailIgnoreCase(eventId, email)) {
            throw new DuplicateRegistrationException("Student with email '" + email + "' is already registered for this event.");
        }

        Registration registration = new Registration(
                requestDTO.getParticipantName().trim(),
                email,
                event
        );

        Registration saved = registrationRepository.save(registration);
        return RegistrationResponseDTO.fromEntity(saved);
    }

    @Override
    public void removeRegistration(Long registrationId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResourceNotFoundException("Registration", "id", registrationId));
        registrationRepository.delete(registration);
    }

    @Override
    @Transactional(readOnly = true)
    public long getRegistrationCountByEventId(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResourceNotFoundException("Event", "id", eventId);
        }
        return registrationRepository.countByEventId(eventId);
    }
}
