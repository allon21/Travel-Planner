package com.travelplanner.controller.privateApi;
import com.travelplanner.dto.CreateTripDto;
import com.travelplanner.dto.TripDto;
import com.travelplanner.dto.UpdateTripDto;
import com.travelplanner.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@Valid @RequestBody CreateTripDto dto,
                                              @RequestHeader("X-Username") String username) {
        TripDto created = tripService.createTrip(dto, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<TripDto>> getAllTrips(@RequestHeader("X-Username") String username) {
        return ResponseEntity.ok(tripService.getAllTripsByUser(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getTripById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable Long id,
                                              @Valid @RequestBody UpdateTripDto dto) {
        return ResponseEntity.ok(tripService.updateTrip(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }
}