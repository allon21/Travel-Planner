package com.travelplanner.dto;

import lombok.Data;

@Data
public class RoutePointDto {
    private Long id;
    private Integer dayOrder;
    private String note;
    private LocationDto location;
}
