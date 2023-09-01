package com.project.assesmentportal.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.assesmentportal.dto.UserDto;
import com.project.assesmentportal.entities.User;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
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
        userDto.setPhoneNumber(1234567890);
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
        
        UserDto savedUserDto = userService.register(userDto);
        assertNotNull(savedUserDto);
        assertEquals(user.getFirstName(), savedUserDto.getFirstName());
        assertEquals(user.getLastName(), savedUserDto.getLastName());
        assertEquals(user.getEmail(), savedUserDto.getEmail());
//        assertEquals(user.getPassword(), savedUserDto.getPassword());
        assertEquals(user.getPhoneNumber(), savedUserDto.getPhoneNumber());
        assertEquals(user.getRole(), savedUserDto.getRole());
    }
    
    

}
