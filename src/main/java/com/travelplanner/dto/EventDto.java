package com.travelplanner.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventDateTime;
    private BigDecimal price;
    private LocationDto location;
}
