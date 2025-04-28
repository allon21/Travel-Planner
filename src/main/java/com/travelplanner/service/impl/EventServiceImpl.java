package com.travelplanner.service.impl;

import com.travelplanner.dto.EventDto;
import com.travelplanner.dto.LocationDto;
import com.travelplanner.entity.Event;
import com.travelplanner.entity.Location;
import com.travelplanner.entity.Trip;
import com.travelplanner.exception.NotFoundException;
import com.travelplanner.mappers.EventMapper;
import com.travelplanner.mappers.LocationMapper;
import com.travelplanner.repository.EventRepository;
import com.travelplanner.service.EventService;
import com.travelplanner.service.TripService;
import com.travelplanner.service.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final TripService tripService;
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @Override
    public EventDto addEvent(Long tripId, EventDto dto) {
        Trip trip = tripService.getTripEntityById(tripId);
        LocationDto locDto = dto.getLocation();
        Location location = (locDto.getId() != null)
                ? locationService.getLocationById(locDto.getId())
                .orElseThrow(() -> new NotFoundException("Location not found"))
                : locationService.createLocation(locationMapper.toEntity(locDto));
        Event event = eventMapper.toEvent(dto);
        event.setTrip(trip);
        event.setLocation(location);
        Event saved = eventRepository.save(event);
        return eventMapper.toEventDto(saved);
    }

    @Override
    public List<EventDto> getEventsByTrip(Long tripId) {
        return eventRepository.findByTripId(tripId).stream()
                .map(eventMapper::toEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto updateEvent(Long id, EventDto dto) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found"));
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setEventDateTime(dto.getEventDateTime());
        existing.setPrice(dto.getPrice());
        Event updated = eventRepository.save(existing);
        return eventMapper.toEventDto(updated);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException("Event not found");
        }
        eventRepository.deleteById(id);
    }
}