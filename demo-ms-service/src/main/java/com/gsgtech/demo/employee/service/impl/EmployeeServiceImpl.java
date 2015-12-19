package com.gsgtech.demo.employee.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsgtech.demo.employee.domain.Employee;
import com.gsgtech.demo.employee.repository.EmployeeRepository;
import com.gsgtech.demo.employee.service.EmployeeService;


/**
 * 
 * @author Jorge Guerrero
 *
 */
@Service("EmployeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<Employee> getAllEmployees(Pageable pageable) {
        log.debug("Solicitud de obtener todos los Empleados");
        return employeeRepository.findAll(pageable); 
	}

	@Override
	@Transactional(readOnly = true)
	public Employee getEmployeeById(Integer id) {
		log.debug("Solicitud de obtener el Empleado : {}", id);
		return employeeRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> getEmployeeByFirstName(String firstName) {
		log.debug("Solicitud de obtener los Empleados filtrados por Nombre : {}", firstName);
		return employeeRepository.findByFirstNameIgnoreCase(firstName);
	}
	
	@Override
    public void deleteEmployee(Integer id) {
    	log.debug("Solicitud de eliminar el Empleado : {}", id);
    	employeeRepository.delete(id);
    }
    
	@Override
    public Employee saveEmployee(Employee employee) {
        log.debug("Solicitud de salvar el Empleado : {}", employee);
        return employeeRepository.save(employee);
    }

}
