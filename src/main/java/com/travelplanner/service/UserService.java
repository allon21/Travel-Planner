package com.travelplanner.service;

import com.travelplanner.dto.UserDto;
import com.travelplanner.entity.User;

import java.util.Optional;

public interface UserService {
    UserDto getCurrentUser(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    User save(User user);
}