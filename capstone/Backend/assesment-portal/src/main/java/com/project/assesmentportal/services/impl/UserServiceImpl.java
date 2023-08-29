package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.entities.User;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.repositories.UserRepository;
import com.project.assesmentportal.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User register(User user) {
		if(!user.getEmail().endsWith("@nucleusteq.com")) {
			throw new ResourceNotFoundException("Email should end with domain @nucleusteq.com");
		}
		Optional<User> checkExistingUser = userRepository.findByEmail(user.getEmail());
		if(checkExistingUser.isPresent()) {
			throw new DuplicateResourceException("The email-id already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
		
	}

	@Override
	public User login(User inputUser) {
		User registeredUser = userRepository.findByEmail(inputUser.getEmail()).orElseThrow(()-> new ResourceNotFoundException("Invalid username or password"));
		
		if(registeredUser!= null && passwordEncoder.matches(inputUser.getPassword(), registeredUser.getPassword()))
		{
			return registeredUser;
		}
		else if(!passwordEncoder.matches(inputUser.getPassword(), registeredUser.getPassword())) {
			throw new ResourceNotFoundException("Invalid credentials");
		}
		return null;
		
		
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
