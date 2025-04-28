package com.travelplanner.service;

import com.travelplanner.dto.EventDto;

import java.util.List;

public interface EventService {
    EventDto addEvent(Long tripId, EventDto dto);
    List<EventDto> getEventsByTrip(Long tripId);
    EventDto updateEvent(Long id, EventDto dto);
    void deleteEvent(Long id);
}