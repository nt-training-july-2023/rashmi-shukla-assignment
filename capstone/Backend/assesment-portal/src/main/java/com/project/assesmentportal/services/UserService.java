package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.dto.UserDto;

/**
 * Service interface for user-related operations.
 */
public interface UserService {

    /**
     * Registers a new user.
     * @param user The UserDto representing the user to be registered.
     * @return The UserDto of the registered user.
     */
    UserDto register(UserDto user);

    /**
     * Performs user login.
     * @param user The UserDto representing the user's login credentials.
     * @return The UserDto of the logged-in user if successful, otherwise null.
     */
    UserDto login(UserDto user);

    /**
     * Retrieves a list of all users.
     * @return A list of UserDto objects representing all registered users.
     */
    List<UserDto> getAllUsers();

}
