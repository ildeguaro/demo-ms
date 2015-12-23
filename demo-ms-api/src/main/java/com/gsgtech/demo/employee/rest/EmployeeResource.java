package com.gsgtech.demo.employee.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gsgtech.demo.employee.domain.Employee;
import com.gsgtech.demo.employee.rest.dto.EmployeeDTO;
import com.gsgtech.demo.employee.rest.mapper.EmployeeMapper;
import com.gsgtech.demo.employee.rest.util.HeaderUtil;
import com.gsgtech.demo.employee.rest.util.PaginationUtil;
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
	
	@Autowired
    private EmployeeMapper employeeMapper;

	/*
	 * Ejemplo: http://127.0.0.1:8080/api/employees?page=0&size=10
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(Pageable pageable) throws URISyntaxException {
		Page<Employee> page = employeeService.getAllEmployees(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employees");
        return new ResponseEntity<>(page.getContent().stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
		EmployeeDTO employee = employeeMapper.employeeToEmployeeDTO(employeeService.getEmployeeById(id));
		return Optional.ofNullable(employee).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/employees/search/{query}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeDTO> getEmployeeByFirstName(@PathVariable String query) {
		log.debug("REST: solicitud para buscar Empleados por Nombre: {}", query);
        return StreamSupport.stream(employeeService.getEmployeeByFirstName(query).spliterator(), false)
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("employee", id.toString())).build();
	}

	@RequestMapping(value = "/employees", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
		log.debug("REST: solicitud para salvar el Empleado: {}", employeeDTO);
		if (employeeDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert("employee", "idexists", "El nuevo Empleado no puede tener ID"))
					.body(null);
		}
		EmployeeDTO result = employeeMapper.employeeToEmployeeDTO(
				employeeService.saveEmployee(employeeMapper.employeeDTOToEmployee(employeeDTO)));
		employeeDTO.setId(result.getId());
		return ResponseEntity.created(new URI("/api/employees/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("employee", result.getId().toString()))
				.body(result);
	}

	@RequestMapping(value = "/employees", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
		log.debug("REST: solicitud para modificar el Empleado: {}", employeeDTO);
		if (employeeDTO.getId() == null) {
			return createEmployee(employeeDTO);
		}
		EmployeeDTO result = employeeMapper.employeeToEmployeeDTO(
				employeeService.saveEmployee(employeeMapper.employeeDTOToEmployee(employeeDTO)));
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert("employee", result.getId().toString()))
				.body(result);
	}

}
