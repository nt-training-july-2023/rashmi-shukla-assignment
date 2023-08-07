package com.springboot.project.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.springbootcrud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
