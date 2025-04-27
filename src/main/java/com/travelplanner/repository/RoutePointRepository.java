package com.travelplanner.repository;

import com.travelplanner.entity.RoutePoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutePointRepository extends JpaRepository<RoutePoint, Long> {
    List<RoutePoint> findByTripId(Long tripId);
}
