package com.travelplanner.service;

import com.travelplanner.dto.CreateTripDto;
import com.travelplanner.dto.TripDto;
import com.travelplanner.dto.UpdateTripDto;
import com.travelplanner.entity.Trip;

import java.util.List;

public interface TripService {
    TripDto createTrip(CreateTripDto dto, String username);
    List<TripDto> getAllTripsByUser(String username);
    TripDto getTripById(Long id);
    TripDto updateTrip(Long id, UpdateTripDto dto);
    void deleteTrip(Long id);
    Trip getTripEntityById(Long id);
}