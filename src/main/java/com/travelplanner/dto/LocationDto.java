package com.travelplanner.dto;

import lombok.Data;

@Data
public class LocationDto {
    private Long id;
    private String city;
    private String country;
    private String address;
    private Double latitude;
    private Double longitude;
}
