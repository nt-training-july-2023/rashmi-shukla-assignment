package com.springboot.project.springbootcrud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.springbootcrud.model.Employee;
import com.springboot.project.springbootcrud.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	//constructor based dependency injection
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService= employeeService ;
	}
	
	//create employee RESTAPI  
	//http://localhost:9111/api/employees + emp info in request body
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	//get all employees RESTAPI
	//http://localhost:9111/api/employees
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	//get emp by id RESTAPI
	// http://localhost:9111/api/employees/1
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long empId) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(empId),HttpStatus.OK);
	}
	
	//update emp RESTAPI
	// http://localhost:9111/api/employees/1  + emp info in request body
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long empId,@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, empId), HttpStatus.OK);
	}
	
	//delete emp RESTAPI
	//http://localhost:9111/api/employees/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long empId){
		employeeService.deleteEmployee(empId);
		return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
	}
}
