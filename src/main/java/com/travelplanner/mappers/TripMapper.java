package com.travelplanner.mappers;

import com.travelplanner.dto.*;
import com.travelplanner.entity.Trip;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {RoutePointMapper.class, EventMapper.class})
public interface TripMapper {

    Trip createDtoToEntity(CreateTripDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateTripDto dto, @MappingTarget Trip trip);

    TripDto entityToDto(Trip trip);
}
