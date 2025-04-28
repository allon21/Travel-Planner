package com.travelplanner.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTripDto {
    @NotBlank
    private String title;
    private String description;

    @Future
    @NotNull
    private LocalDate startDate;
    @Future @NotNull private LocalDate endDate;

    @PositiveOrZero
    @NotNull private Double budget;
}