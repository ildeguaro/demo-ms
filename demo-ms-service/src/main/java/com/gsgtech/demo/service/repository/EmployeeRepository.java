package com.gsgtech.demo.service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gsgtech.demo.domain.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	public List<Employee> findByFirstNameIgnoreCase(String firstName);
	
}
