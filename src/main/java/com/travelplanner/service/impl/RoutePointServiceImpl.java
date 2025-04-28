package com.travelplanner.service.impl;

import com.travelplanner.dto.RoutePointDto;
import com.travelplanner.dto.LocationDto;
import com.travelplanner.entity.Location;
import com.travelplanner.entity.RoutePoint;
import com.travelplanner.entity.Trip;
import com.travelplanner.exception.NotFoundException;
import com.travelplanner.mappers.RoutePointMapper;
import com.travelplanner.mappers.LocationMapper;
import com.travelplanner.repository.RoutePointRepository;
import com.travelplanner.service.RoutePointService;
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
public class RoutePointServiceImpl implements RoutePointService {

    private final RoutePointRepository routePointRepository;
    private final RoutePointMapper routePointMapper;
    private final TripService tripService;
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @Override
    public RoutePointDto addRoutePoint(Long tripId, RoutePointDto dto) {
        Trip trip = tripService.getTripEntityById(tripId);
        LocationDto locDto = dto.getLocation();
        Location location = (locDto.getId() != null)
                ? locationService.getLocationById(locDto.getId())
                .orElseThrow(() -> new NotFoundException("Location not found"))
                : locationService.createLocation(locationMapper.toEntity(locDto));
        RoutePoint rp = routePointMapper.toEntity(dto);
        rp.setTrip(trip);
        rp.setLocation(location);
        RoutePoint saved = routePointRepository.save(rp);
        return routePointMapper.toDto(saved);
    }

    @Override
    public List<RoutePointDto> getRoutePointsByTrip(Long tripId) {
        return routePointRepository.findByTripId(tripId).stream()
                .map(routePointMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoutePointDto updateRoutePoint(Long id, RoutePointDto dto) {
        RoutePoint existing = routePointRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Route point not found"));
        existing.setDayOrder(dto.getDayOrder());
        existing.setNote(dto.getNote());
        RoutePoint updated = routePointRepository.save(existing);
        return routePointMapper.toDto(updated);
    }

    @Override
    public void deleteRoutePoint(Long id) {
        if (!routePointRepository.existsById(id)) {
            throw new NotFoundException("Route point not found");
        }
        routePointRepository.deleteById(id);
    }
}