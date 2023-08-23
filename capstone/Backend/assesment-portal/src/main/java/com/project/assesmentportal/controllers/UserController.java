package com.project.assesmentportal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.assesmentportal.entities.User;
import com.project.assesmentportal.services.UserService;

@RestController

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register", method =RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user){
		return new ResponseEntity<User>(userService.register(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/users", method =RequestMethod.GET)
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	@RequestMapping(value="/login", method =RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody User user){
		if(userService.login(user) == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("wrong credentials");
		}
		return ResponseEntity.ok(userService.login(user).getFirstName()+" Successfully logged in");
		
	}
}
