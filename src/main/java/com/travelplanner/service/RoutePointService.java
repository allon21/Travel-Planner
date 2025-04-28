package com.travelplanner.service;

import com.travelplanner.dto.RoutePointDto;
import java.util.List;

public interface RoutePointService {
    RoutePointDto addRoutePoint(Long tripId, RoutePointDto dto);
    List<RoutePointDto> getRoutePointsByTrip(Long tripId);
    RoutePointDto updateRoutePoint(Long id, RoutePointDto dto);
    void deleteRoutePoint(Long id);
}