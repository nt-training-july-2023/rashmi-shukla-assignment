package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.entities.User;

public interface UserService {
	
	User register(User user);
	User login(User user);
	List<User> getAllUsers();
	
}
