package com.gsgtech.demos.serva.api;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsgtech.demo.domain.Employee;
import com.gsgtech.demo.service.EmployeeService;


/**
 *
 * @author ildemaro-medina
 */

@RestController
@RequestMapping(value = "/employees")
public class EmployeesController { 
    
        
    @Autowired
    private EmployeeService  employeeService;
        
    @RequestMapping("/")  
    @ResponseBody
    public ResponseEntity<List<Employee>> getAll() {
    	List<Employee> lista = employeeService.getAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    @RequestMapping("/byid/{employeeId}")
    @ResponseBody
    public ResponseEntity<Employee> getById(@PathVariable int employeeId) {
    	Employee objeto = employeeService.getById(employeeId);
        return new ResponseEntity<Employee>(objeto, HttpStatus.OK);
    }
    
    @RequestMapping("/byfirstname/{firstName}")
    @ResponseBody
    public ResponseEntity<List<Employee>> getByFirstName(@PathVariable String firstName) {
    	List<Employee> lista = employeeService.getByFirstName(firstName);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
   
    
}
