package com.project.assesmentportal.controllers;

import java.util.List;

/**
 * Controller class for user login and register.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.assesmentportal.dto.UserDto;
import com.project.assesmentportal.services.UserService;

/**
 * Controller class responsible for user registration, login, and retrieval
 * of user data.
 */
@CrossOrigin("*")
@RestController
public class UserController {

    /**
     * instance of UserService
     */
    @Autowired
    private UserService userService;

    /**
     * Registers a new user.
     * @param userDto The UserDto representing the user to be registered.
     * @return A ResponseEntity with a success message upon successful
     *         registration or a bad request response in case of failure.
     */
    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public final ResponseEntity<String> register(
            @RequestBody final UserDto userDto) {
        UserDto registerdUser = userService.register(userDto);
        if (registerdUser == null) {
            return ResponseEntity.badRequest().body("Invalid");
        }
        return ResponseEntity.ok(
                registerdUser.getFirstName() + " Registered Successfully");
    }

    /**
     * Retrieves a list of all users.
     * @return A list of UserDto objects representing all registered users.
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public final List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Handles user login.
     * @param userDto The UserDto representing the user's login
     *                credentials.
     * @return A ResponseEntity with a success message containing the
     *         user's role upon successful login, or an unauthorized
     *         response in case of failure.
     */
    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public final ResponseEntity<String> login(
            @RequestBody final UserDto userDto) {
        if (userService.login(userDto) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("incorrect credentials");
        }
//		return ResponseEntity.ok(userService.login(user).getFirstName()+" Successfully logged in");
        return ResponseEntity.ok(userService.login(userDto).getRole());

    }
}
