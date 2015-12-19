package com.gsgtech.demo.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsgtech.demo.employee.domain.Employee;

/**
 * 
 * @author Ildemaro Médina
 * @author Jorge Guerrero
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public List<Employee> findByFirstNameIgnoreCase(String firstName);

}
