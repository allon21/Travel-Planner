package com.travelplanner.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TripDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double budget;
    private String status;
    private List<RoutePointDto> routePoints;
    private List<EventDto> events;
}
