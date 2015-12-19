package com.gsgtech.demo.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gsgtech.demo.employee.domain.Employee;


public interface EmployeeService {
	
	public Page<Employee> getAllEmployees(Pageable pageable);

	public Employee getEmployeeById(Integer id);

	public List<Employee> getEmployeeByFirstName(String fisrName);
	
	public void deleteEmployee(Integer id);

	public Employee saveEmployee(Employee employee);
	
}
