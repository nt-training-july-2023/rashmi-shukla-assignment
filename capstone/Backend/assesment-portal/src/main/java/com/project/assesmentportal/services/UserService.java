package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.dto.UserDto;

public interface UserService {
    UserDto register(UserDto user);

    UserDto login(UserDto user);

    List<UserDto> getAllUsers();

}
