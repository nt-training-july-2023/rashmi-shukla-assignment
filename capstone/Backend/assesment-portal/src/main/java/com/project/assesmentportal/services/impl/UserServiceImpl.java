package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Registers a new user.
     * @param userDto The UserDto representing the user to be registered.
     * @return The UserDto of the registered user.
     * @throws ResourceNotFoundException if the email is already registered.
     */
    @Override
    public final String register(final UserDto userDto) {
        LOGGER.info("Service: User Registration invoked.");
        User user = this.dtoToUser(userDto);
        Optional<User> checkExistingUser = userRepository
                .findByEmail(user.getEmail());
        if (checkExistingUser.isPresent()) {
            LOGGER.error("Registration error: email id already exists");
            throw new DuplicateResourceException(
                    "The email-id already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        LOGGER.info("Service: User registered successfully.");
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
        LOGGER.info("Service: Login method invoked.");
        User registeredUser = userRepository
                .findByEmail(inputUserDto.getEmail())
                .orElseGet(() -> {
                    LOGGER.error("Login error: user not found");
                    throw new ResourceNotFoundException(
                        "Invalid email or password");
                });

        if (!passwordEncoder.matches(inputUserDto.getPassword(),
                registeredUser.getPassword())) {
            LOGGER.error("Login error: invalid credentials");
            throw new InvalidDataException("Invalid credentials");
        }
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setFullName(registeredUser.getFirstName() + " "
                + registeredUser.getLastName());
        loginResponseDto.setRole(registeredUser.getRole());
        loginResponseDto.setEmail(registeredUser.getEmail());
        loginResponseDto.setMessage(
                registeredUser.getFirstName() + "logged-in successfully");
        LOGGER.info("Service: User logged-in successfully.");
        return loginResponseDto;
    }

    /**
     * Retrieves a list of all users.
     * @return A list of UserDto objects representing all registered users.
     */
    @Override
    public final List<UserDto> getUsers() {
        LOGGER.info("Service: getUsers methods invoked.");
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> this.userToDto(user))
                .collect(Collectors.toList());
        LOGGER.info("Service: Retrieved list of users successfully.");
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
