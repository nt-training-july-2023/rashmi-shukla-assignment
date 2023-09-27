package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.dto.LoginRequestDto;
import com.project.assesmentportal.dto.LoginResponseDto;
import com.project.assesmentportal.dto.UserDto;
import com.project.assesmentportal.entities.User;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.InvalidDataException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.repositories.UserRepository;
import com.project.assesmentportal.services.UserService;

/**
 * Implementation of the UserService interface for managing user-related
 * operations.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * instance of Modelmapper.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * instance of UserRepository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * instance of passwordEncoder class.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user.
     * @param userDto The UserDto representing the user to be registered.
     * @return The UserDto of the registered user.
     * @throws ResourceNotFoundException if the email is already registered.
     */
    @Override
    public final String register(final UserDto userDto) {
        User user = this.dtoToUser(userDto);
        Optional<User> checkExistingUser = userRepository
                .findByEmail(user.getEmail());
        if (checkExistingUser.isPresent()) {
            throw new DuplicateResourceException(
                    "The email-id already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userDto.getFirstName() + " registered successfully!";

    }

    /**
     * Performs user login.
     * @param inputUserDto The UserDto representing the user's login.
     * @return The UserDto of the logged-in user if successful.
     * @throws ResourceNotFoundException If the provided username or
     *                                   password is invalid.
     */
    @Override
    public final LoginResponseDto login(
            final LoginRequestDto inputUserDto) {
        User registeredUser = userRepository
                .findByEmail(inputUserDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Invalid email or password"));

        if (!passwordEncoder.matches(inputUserDto.getPassword(),
                registeredUser.getPassword())) {
            throw new InvalidDataException("Invalid credentials");
        }
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setFullName(registeredUser.getFirstName() + " "
                + registeredUser.getLastName());
        loginResponseDto.setRole(registeredUser.getRole());
        loginResponseDto.setEmail(registeredUser.getEmail());
        loginResponseDto.setMessage(
                registeredUser.getFirstName() + "logged-in successfully");
        return loginResponseDto;
    }

    /**
     * Retrieves a list of all users.
     * @return A list of UserDto objects representing all registered users.
     */
    @Override
    public final List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> this.userToDto(user))
                .collect(Collectors.toList());
        return userDtos;
    }

    /**
     * Converts userDto to user.
     * @param userDto representing userDto details.
     * @return user.
     */
    public final User dtoToUser(final UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    /**
     * converts user to userDto.
     * @param user representing user details
     * @return userDto
     */
    public final UserDto userToDto(final User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

}
