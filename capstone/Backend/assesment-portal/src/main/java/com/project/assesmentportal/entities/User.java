package com.project.assesmentportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increments the primary key
	private long userId;
	
	@Column(name = "first_name", nullable = false) //optional, default col gets created as firstName
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "phone_number")
	private long phoneNumber;
	
	private String role = "user";
}
