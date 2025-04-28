package com.travelplanner.service;

import com.travelplanner.entity.Location;

import java.util.Optional;

public interface LocationService {
    Location createLocation(Location location);
    Optional<Location> getLocationById(Long id);
}