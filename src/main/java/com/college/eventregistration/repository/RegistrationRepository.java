package com.college.eventregistration.repository;

import com.college.eventregistration.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByEventIdOrderByRegisteredAtDesc(Long eventId);

    long countByEventId(Long eventId);

    boolean existsByEventIdAndParticipantEmailIgnoreCase(Long eventId, String participantEmail);

    void deleteByEventId(Long eventId);
}
