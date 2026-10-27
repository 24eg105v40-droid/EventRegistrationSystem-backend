package com.college.eventregistration.service;

import com.college.eventregistration.dto.RegistrationRequestDTO;
import com.college.eventregistration.dto.RegistrationResponseDTO;

import java.util.List;

public interface RegistrationService {

    List<RegistrationResponseDTO> getRegistrationsByEventId(Long eventId);

    RegistrationResponseDTO registerParticipant(Long eventId, RegistrationRequestDTO requestDTO);

    void removeRegistration(Long registrationId);

    long getRegistrationCountByEventId(Long eventId);
}
