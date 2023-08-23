package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.entities.User;
import com.project.assesmentportal.exceptions.DuplicateEmailException;
import com.project.assesmentportal.exceptions.UserNotFoundException;
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
		Optional<User> checkExistingUser = userRepository.findByEmail(user.getEmail());
		if(checkExistingUser.isPresent()) {
			throw new DuplicateEmailException("The email-id already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
		
	}

	@Override
	public User login(User inputUser) {
		User registeredUser = userRepository.findByEmail(inputUser.getEmail()).orElseThrow(()-> new UserNotFoundException());
		if(registeredUser!= null && passwordEncoder.matches(inputUser.getPassword(), registeredUser.getPassword()))
		{
			return registeredUser;
		}
		return null;
		
		
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
