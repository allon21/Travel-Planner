package com.travelplanner.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UpdateTripDto {
    @NotBlank
    private String title;
    private String description;

    @Future
    @NotNull
    private LocalDate startDate;
    @Future
    @NotNull
    private LocalDate endDate;

    @PositiveOrZero
    @NotNull
    private Double budget;
    private String status;

    @AssertTrue(message = "endDate must be after startDate")
    public boolean isDatesValid() {
        return startDate != null && endDate != null && endDate.isAfter(startDate);
    }
}