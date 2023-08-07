package com.springboot.project.springbootcrud.service;

import java.util.List;

import com.springboot.project.springbootcrud.model.Employee;

public interface EmployeeService {
	//return-type nameofMethod(args);
	
	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);
	Employee updateEmployee(Employee employee, long id);
	void deleteEmployee(long id);
}
