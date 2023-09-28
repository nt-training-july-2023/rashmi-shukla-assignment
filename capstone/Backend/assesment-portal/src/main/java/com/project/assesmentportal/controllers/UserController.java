package com.project.assesmentportal.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.assesmentportal.dto.LoginRequestDto;
import com.project.assesmentportal.dto.LoginResponseDto;
import com.project.assesmentportal.dto.UserDto;
import com.project.assesmentportal.services.UserService;

import jakarta.validation.Valid;

/**
 * Controller class responsible for user registration, login, and retrieval
 * of user data.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * instance of UserService.
     */
    @Autowired
    private UserService userService;

    /**
     * This creates Logger Instance.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserController.class);

    /**
     * Registers a new user.
     * @param userDto The UserDto representing the user to be registered.
     * @return A ResponseEntity with a success message upon successful
     *         registration or a bad request response in case of failure.
     */
    @PostMapping("/register")
    public final ResponseEntity<String> register(
            @RequestBody @Valid final UserDto userDto) {
        LOGGER.info("User registration method invoked.");
        String response = userService.register(userDto);
        LOGGER.info("User Registered Successfully.");
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all users.
     * @return A list of UserDto objects representing all registered users.
     */
    @GetMapping()
    public final List<UserDto> getUsers() {
        LOGGER.info("Retrieving list of users.");
        List<UserDto> usersDtos =  userService.getUsers();
        LOGGER.info("Retrieved list of users successfully.");
        return usersDtos;
    }

    /**
     * Handles user login.
     * @param loginRequestDto representing the user's login
     *                credentials.
     * @return A ResponseEntity with a success message containing the
     *         user's role upon successful login, or an unauthorized
     *         response in case of failure.
     */
    @PostMapping("/login")
    public final ResponseEntity<LoginResponseDto> login(
            @RequestBody @Valid final LoginRequestDto loginRequestDto) {
        LOGGER.info("Login method invoked.");
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        LOGGER.info("User logged-in successfully");
        return ResponseEntity.ok(loginResponseDto);

    }
}
