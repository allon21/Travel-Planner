package com.travelplanner.mappers;

import com.travelplanner.dto.RoutePointDto;
import com.travelplanner.entity.RoutePoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface RoutePointMapper {
    RoutePoint toEntity(RoutePointDto dto);
    RoutePointDto toDto(RoutePoint entity);
}
