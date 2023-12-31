package com.project.assesmentportal.controllers;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.assesmentportal.dto.UserDto;
import com.project.assesmentportal.repositories.UserRepository;
import com.project.assesmentportal.services.impl.UserServiceImpl;

class UserControllerTest {
    
    @InjectMocks
    private UserController userController;
    
    @Mock
    private UserServiceImpl userService;
    
    @Mock
    private UserRepository userRepository;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testRegister_Sucess() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Rashmi");
        when(userService.register(userDto)).thenReturn(userDto);
        ResponseEntity<String> response = userController.register(userDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rashmi Registered Successfully", response.getBody());
    }
    
    @Test
    public void testRegister_Invalid() {
        UserDto userDto = new UserDto();
        when(userService.register(userDto)).thenReturn(null);
        ResponseEntity<String> response = userController.register(new UserDto());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid", response.getBody());
    }
    @Test
    public void testLogin_Success() {
        UserDto userDto = new UserDto(); 
        when(userService.login(userDto)).thenReturn(userDto);

        ResponseEntity<String> response = userController.login(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto.getRole(), response.getBody());
    }
    
    @Test
    public void testLoginWithInvalidUser() {
        UserDto userDto = new UserDto(); // Create an invalid UserDto object
        when(userService.login(userDto)).thenReturn(null);

        ResponseEntity<String> response = userController.login(userDto);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("incorrect credentials", response.getBody());
    }
    
    @Test
    public void testGetAllUser() {
        List<UserDto> users = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(users);
        List<UserDto> response = userController.getAllUsers();
        assertEquals(users, response);
    }

}
