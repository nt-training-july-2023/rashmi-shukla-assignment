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
        when(userService.register(userDto)).thenReturn(userDto.getFirstName()+" registered successfully!");
        ResponseEntity<String> response = userController.register(userDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto.getFirstName()+" registered successfully!", response.getBody());
    }
    
    @Test
    public void testLogin_Success() {
        UserDto userDto = new UserDto(); 
        when(userService.login(userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.login(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }
    
    @Test
    public void testLoginWithInvalidUser() {
        UserDto userDto = new UserDto(); // Create an invalid UserDto object
        when(userService.login(userDto)).thenReturn(null);

        ResponseEntity<UserDto> response = userController.login(userDto);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
    
    @Test
    public void testGetAllUser() {
        List<UserDto> users = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(users);
        List<UserDto> response = userController.getAllUsers();
        assertEquals(users, response);
    }

}
