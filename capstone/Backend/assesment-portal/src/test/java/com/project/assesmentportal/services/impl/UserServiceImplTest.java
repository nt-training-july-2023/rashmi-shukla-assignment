package com.project.assesmentportal.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.assesmentportal.dto.ApiResponse;
import com.project.assesmentportal.dto.LoginRequestDto;
import com.project.assesmentportal.dto.LoginResponseDto;
import com.project.assesmentportal.dto.UserDto;
import com.project.assesmentportal.entities.User;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.InvalidDataException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.messages.MessageConstants;
import com.project.assesmentportal.repositories.UserRepository;

class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testRegisterUser_Success() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Rashmi");
        userDto.setLastName("Shukla");
        userDto.setEmail("test@nucleusteq.com");
        userDto.setPassword("12345");
        userDto.setPhoneNumber("9234567890");
        userDto.setRole("user");
          
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRole(userDto.getRole());
        
        
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        ApiResponse response = userService.register(userDto);
        
        assertNotNull(response);
        assertEquals(MessageConstants.USER_REGISTERED_SUCCESSFULLY,response.getMessage());
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        
    }
    
    @Test
    public void testRegisterUser_DuplicateEmail() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Rashmi");
        userDto.setLastName("Shukla");
        userDto.setEmail("test@nucleusteq.com");
        userDto.setPassword("12345");
        userDto.setPhoneNumber("9234567890");
        userDto.setRole("user");
          
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRole(userDto.getRole());
        
        
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        assertThrows(DuplicateResourceException.class, () -> {
            userService.register(userDto);
        });
    }
    
    @Test
    public void testLoginUser_Success() {
        LoginRequestDto userDto = new LoginRequestDto();
        userDto.setEmail("test@nucleusteq.com");
        userDto.setPassword("12345");
          
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        
        LoginResponseDto savedUserDto = userService.login(userDto);
        assertNotNull(savedUserDto);
        assertEquals(user.getEmail(), savedUserDto.getEmail());
    }
    
    @Test
    public void testInvalidUsernameOrPassword() {
        LoginRequestDto inputUserDto = new LoginRequestDto();
        inputUserDto.setEmail("test@nucleusteq.com");
        inputUserDto.setPassword("12345");
        
        User inputUser = new User();
        inputUser.setEmail(inputUserDto.getEmail());
        inputUser.setPassword(inputUser.getPassword());

        when(userRepository.findByEmail(inputUserDto.getEmail())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.login(inputUserDto);
        });
    }
    
    @Test
    public void testInvalidCredentials() {
        LoginRequestDto inputUserDto = new LoginRequestDto();
        inputUserDto.setEmail("test@nucleusteq.com");
        inputUserDto.setPassword("12345");
        
        User inputUser = new User();
        inputUser.setEmail(inputUserDto.getEmail());
        inputUser.setPassword(inputUser.getPassword());

        when(userRepository.findByEmail(inputUserDto.getEmail())).thenReturn(Optional.of(inputUser));
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        assertThrows(InvalidDataException.class, () -> {
            userService.login(inputUserDto);
        });
    }
    
    @Test
    public void testGetUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"Rashmi","Shukla","rs@gmail.com","12345","9234567890","user"));
        userList.add(new User(2,"Pranjal","Yadav","py@gmail.com","135689","9298567890","admin"));

        when(userRepository.findAll()).thenReturn(userList);
        List<UserDto> userDtos = userService.getUsers();

        // Assert
        assertNotNull(userDtos);
        assertEquals(2, userDtos.size()); // Assuming there are 2 users in the list
    }
    
    

}
