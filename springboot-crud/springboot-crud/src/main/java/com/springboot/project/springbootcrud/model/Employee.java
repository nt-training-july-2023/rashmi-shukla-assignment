package com.springboot.project.springbootcrud.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increments the primary key
	private long id;
	
	@Column(name = "first_name", nullable = false) //optional, default col gets created as firstName
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;

}
