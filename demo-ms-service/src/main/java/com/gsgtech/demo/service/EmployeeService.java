package com.gsgtech.demo.service;

import java.util.List;

import com.gsgtech.demo.domain.Employee;


public interface EmployeeService {
	public List<Employee> getAll();
	public Employee getById(int id);	
	public List<Employee> getByFirstName(String fisrName);
}
