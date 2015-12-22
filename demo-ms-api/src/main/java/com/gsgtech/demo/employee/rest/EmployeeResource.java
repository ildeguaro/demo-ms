package com.gsgtech.demo.employee.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gsgtech.demo.employee.domain.Employee;
import com.gsgtech.demo.employee.dto.EmployeeDTO;
import com.gsgtech.demo.employee.rest.util.EmployeeCovertUtil;
import com.gsgtech.demo.employee.rest.util.HeaderUtil;
import com.gsgtech.demo.employee.service.EmployeeService;


/**
 * 
 * @author Jorge Guerrero
 *
 */
@RestController
@RequestMapping(value = "/api")
public class EmployeeResource {
    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

	@Autowired
	private EmployeeService employeeService;

	/*
	 * Ejemplo: http://127.0.0.1:8080/api/employees/?page=0&size=10
	 */
    @RequestMapping(value = "/employees",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getAllEmployees(Pageable pageable) {
    	Page<Employee> page = employeeService.getAllEmployees(pageable);
		return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
	}

    @RequestMapping(value = "/employees/{id}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
		EmployeeDTO employee = EmployeeCovertUtil.convertToDto(employeeService.getEmployeeById(id));
        return Optional.ofNullable(employee)
            .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/employees/search/{query}",
	    method = RequestMethod.GET,
	    produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeDTO> getEmployeeByFirstName(@PathVariable String query) {
		log.debug("REST: solicitud para buscar Empleados por Nombre: {}", query);
		List<EmployeeDTO> result = EmployeeCovertUtil.convertToListDto(employeeService.getEmployeeByFirstName(query));
		return result;
	}
	
    @RequestMapping(value = "/employees/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
    	employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
    
    @RequestMapping(value = "/employees", 
    		method = RequestMethod.POST, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDto) throws URISyntaxException {
    		log.debug("REST: solicitud para salvar el Empleado: {}", employeeDto);
    		if (employeeDto.getId() != null) {
    			return ResponseEntity.badRequest().headers(
    					HeaderUtil.createFailureAlert("employee", "idexists", "El nuevo Empleado no puede tener ID"))
    					.body(null);
    		}
    		Employee result = employeeService.saveEmployee(EmployeeCovertUtil.convertToEntity(employeeDto));
    		employeeDto.setId(result.getId());
    		return ResponseEntity.created(new URI("/api/employees/" + employeeDto.getId()))
    				.headers(HeaderUtil.createEntityCreationAlert("employee", employeeDto.getId().toString())).body(employeeDto);
    	}
	
    @RequestMapping(value = "/employees", 
    		method = RequestMethod.PUT, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    	public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDto) throws URISyntaxException {
    		log.debug("REST: solicitud para modificar el Empleado: {}", employeeDto);
    		if (employeeDto.getId() == null) {
    			return createEmployee(employeeDto);
    		}
    		Employee result = employeeService.saveEmployee(EmployeeCovertUtil.convertToEntity(employeeDto));
    		employeeDto.setId(result.getId());
    		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("employee", 
    				employeeDto.getId().toString())).body(employeeDto);
    	}

	
	

}
