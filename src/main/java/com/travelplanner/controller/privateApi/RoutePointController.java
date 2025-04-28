package com.travelplanner.controller.privateApi;

import com.travelplanner.dto.RoutePointDto;
import com.travelplanner.service.RoutePointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/trips/{tripId}/route-points")
@RequiredArgsConstructor
public class RoutePointController {
    private final RoutePointService routePointService;

    @PostMapping
    public ResponseEntity<RoutePointDto> addRoutePoint(@PathVariable Long tripId,
                                                       @Valid @RequestBody RoutePointDto dto) {
        RoutePointDto created = routePointService.addRoutePoint(tripId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<RoutePointDto>> getRoutePoints(@PathVariable Long tripId) {
        return ResponseEntity.ok(routePointService.getRoutePointsByTrip(tripId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutePointDto> updateRoutePoint(@PathVariable Long tripId,
                                                          @PathVariable Long id,
                                                          @Valid @RequestBody RoutePointDto dto) {
        RoutePointDto updated = routePointService.updateRoutePoint(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutePoint(@PathVariable Long tripId,
                                                 @PathVariable Long id) {
        routePointService.deleteRoutePoint(id);
        return ResponseEntity.noContent().build();
    }
}
