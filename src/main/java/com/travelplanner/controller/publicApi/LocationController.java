package com.travelplanner.controller.publicApi;


import com.travelplanner.dto.LocationDto;
import com.travelplanner.entity.Location;
import com.travelplanner.exception.NotFoundException;
import com.travelplanner.mappers.LocationMapper;
import com.travelplanner.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@Valid @RequestBody LocationDto dto) {
        Location loc = locationMapper.toEntity(dto);
        Location saved = locationService.createLocation(loc);
        return ResponseEntity.status(HttpStatus.CREATED).body(locationMapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocation(@PathVariable Long id) {
        Location location = locationService.getLocationById(id)
                .orElseThrow(() -> new NotFoundException("Location not found"));
        return ResponseEntity.ok(locationMapper.toDto(location));
    }
}
