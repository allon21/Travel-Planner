package com.travelplanner.mappers;

import com.travelplanner.dto.EventDto;
import com.travelplanner.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toEventDto(Event event);

    Event toEvent(EventDto eventDto);
}