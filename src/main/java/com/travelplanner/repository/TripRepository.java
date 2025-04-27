package com.travelplanner.repository;


import com.travelplanner.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByOwnerId(Long ownerId);
}
