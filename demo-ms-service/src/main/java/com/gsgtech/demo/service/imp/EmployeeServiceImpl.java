/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gsgtech.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsgtech.demo.domain.Employee;
import com.gsgtech.demo.service.EmployeeService;
import com.gsgtech.demo.service.repository.EmployeeRepository;

/**
 *
 * @author ildemaro-medina
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    
	@Autowired
	private EmployeeRepository employeeRepository;
	
		        
    public List<Employee> getAll(){    	
    	return (List<Employee>) employeeRepository.findAll();
    }


	@Override
	public Employee getById(int id) {		
		return employeeRepository.findOne(id);
	}


	@Override
	public List<Employee> getByFirstName(String firstName) {		
		return employeeRepository.findByFirstNameIgnoreCase(firstName);
	}
	
	
    
}
