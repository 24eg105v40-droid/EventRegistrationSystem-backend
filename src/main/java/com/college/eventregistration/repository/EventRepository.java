package com.college.eventregistration.repository;

import com.college.eventregistration.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByOrderByDateAsc();

    List<Event> findAllByOrderByIdDesc();

    @Query("SELECT e, COUNT(r) FROM Event e LEFT JOIN e.registrations r GROUP BY e.id ORDER BY e.date ASC")
    List<Object[]> findAllEventsWithRegistrationCount();
}
