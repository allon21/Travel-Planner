package com.travelplanner.mappers;

import com.travelplanner.dto.UserDto;
import com.travelplanner.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}
