package com.travelplanner.mappers;


import com.travelplanner.dto.LocationDto;
import com.travelplanner.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toEntity(LocationDto dto);
    LocationDto toDto(Location entity);
}
