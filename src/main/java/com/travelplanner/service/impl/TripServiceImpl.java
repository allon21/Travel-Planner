package com.travelplanner.service.impl;

import com.travelplanner.dto.CreateTripDto;
import com.travelplanner.dto.TripDto;
import com.travelplanner.dto.UpdateTripDto;
import com.travelplanner.entity.Trip;
import com.travelplanner.entity.TripStatus;
import com.travelplanner.entity.User;
import com.travelplanner.exception.NotFoundException;
import com.travelplanner.mappers.TripMapper;
import com.travelplanner.repository.TripRepository;
import com.travelplanner.service.TripService;
import com.travelplanner.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final UserService userService;

    @Override
    public TripDto createTrip(CreateTripDto dto, String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Trip trip = tripMapper.createDtoToEntity(dto);
        trip.setOwner(user);
        trip.setStatus(TripStatus.PLANNED);
        Trip saved = tripRepository.save(trip);
        return tripMapper.entityToDto(saved);
    }

    @Override
    public List<TripDto> getAllTripsByUser(String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return tripRepository.findByOwnerId(user.getId()).stream()
                .map(tripMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TripDto getTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trip not found"));
        return tripMapper.entityToDto(trip);
    }

    @Override
    public TripDto updateTrip(Long id, UpdateTripDto dto) {
        Trip existing = tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trip not found"));
        tripMapper.updateEntityFromDto(dto, existing);
        Trip updated = tripRepository.save(existing);
        return tripMapper.entityToDto(updated);
    }

    @Override
    public void deleteTrip(Long id) {
        if (!tripRepository.existsById(id)) {
            throw new NotFoundException("Trip not found");
        }
        tripRepository.deleteById(id);
    }

    @Override
    public Trip getTripEntityById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trip not found"));
    }
}
