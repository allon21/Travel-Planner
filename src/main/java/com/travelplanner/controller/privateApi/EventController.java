package com.travelplanner.controller.privateApi;

import com.travelplanner.dto.EventDto;
import com.travelplanner.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/trips/{tripId}/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventDto> addEvent(@PathVariable Long tripId,
                                             @Valid @RequestBody EventDto dto) {
        EventDto created = eventService.addEvent(tripId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> getEvents(@PathVariable Long tripId) {
        return ResponseEntity.ok(eventService.getEventsByTrip(tripId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long tripId,
                                                @PathVariable Long id,
                                                @Valid @RequestBody EventDto dto) {
        EventDto updated = eventService.updateEvent(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long tripId,
                                            @PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
