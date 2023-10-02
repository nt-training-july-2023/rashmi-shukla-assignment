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

import com.project.assesmentportal.dto.ApiResponse;
import com.project.assesmentportal.dto.LoginRequestDto;
import com.project.assesmentportal.dto.LoginResponseDto;
import com.project.assesmentportal.dto.UserDto;
import com.project.assesmentportal.messages.MessageConstants;
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
        ApiResponse apiResponse = new ApiResponse(MessageConstants.USER_REGISTERED_SUCCESSFULLY, HttpStatus.CREATED.value());
        when(userService.register(userDto)).thenReturn(apiResponse);
        ResponseEntity<ApiResponse> response = userController.register(userDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(apiResponse, response.getBody());
        assertEquals(201, response.getBody().getStatus());
    }
    
    @Test
    public void testLogin_Success() {
        LoginRequestDto userDto = new LoginRequestDto(); 
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        when(userService.login(userDto)).thenReturn(loginResponseDto);

        ResponseEntity<LoginResponseDto> response = userController.login(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loginResponseDto, response.getBody());
    }
    
    @Test
    public void testLoginWithInvalidUser() {
        LoginRequestDto userDto = new LoginRequestDto(); // Create an invalid UserDto object
        when(userService.login(userDto)).thenReturn(null);

        ResponseEntity<LoginResponseDto> response = userController.login(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
    
    @Test
    public void testGetUsers() {
        List<UserDto> users = new ArrayList<>();
        when(userService.getUsers()).thenReturn(users);
        List<UserDto> response = userController.getUsers();
        assertEquals(users, response);
    }

}
