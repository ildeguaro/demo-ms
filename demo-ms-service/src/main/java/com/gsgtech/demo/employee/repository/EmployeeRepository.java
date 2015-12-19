package com.gsgtech.demo.employee.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.gsgtech.demo.employee.domain.Employee;

/**
 * 
 * @author Ildemaro Médina
 * @author Jorge Guerrero
 *
 */
public interface EmployeeRepository extends Repository<Employee, Integer> {

	List<Employee> findByFirstNameIgnoreCase(String firstName);
	
	Employee findOne(Integer id);
	
	Page<Employee> findAll(Pageable pageable);
	
	Employee save(Employee entity);
	
	void delete(Integer id);

}
