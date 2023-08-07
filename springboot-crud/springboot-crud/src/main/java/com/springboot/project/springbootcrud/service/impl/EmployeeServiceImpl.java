package com.springboot.project.springbootcrud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.project.springbootcrud.exception.ResourceNotFoundException;
import com.springboot.project.springbootcrud.model.Employee;
import com.springboot.project.springbootcrud.repository.EmployeeRepository;
import com.springboot.project.springbootcrud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	//constructor based dependency injection
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get(); 
		}else{
			throw new ResourceNotFoundException("Employee","id",id);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//if emp exists in db
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee","id",id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//save to db
		employeeRepository.save(existingEmployee);
		return existingEmployee;
		
	}

	@Override
	public void deleteEmployee(long id) {
		//check if emp id exists
		employeeRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee","id",id));
		
		employeeRepository.deleteById(id);
		
	}
	
	
}
