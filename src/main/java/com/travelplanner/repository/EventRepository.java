package com.travelplanner.repository;

import com.travelplanner.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTripId(Long tripId);
}
