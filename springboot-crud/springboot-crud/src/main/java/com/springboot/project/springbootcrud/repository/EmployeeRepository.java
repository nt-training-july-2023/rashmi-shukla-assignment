package com.springboot.project.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.springbootcrud.model.Employee;
// 													type of entity, type of primary key
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
