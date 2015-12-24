package com.gsgtech.demo.employee.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gsgtech.demo.employee.domain.Employee;
import com.gsgtech.demo.employee.rest.dto.EmployeeDTO;
import com.gsgtech.demo.employee.rest.mapper.EmployeeMapper;
import com.gsgtech.demo.employee.rest.util.HeaderUtil;
import com.gsgtech.demo.employee.rest.util.PageResource;
import com.gsgtech.demo.employee.service.EmployeeService;

/**
 * 
 * @author Jorge Guerrero
 *
 */
@Controller
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
	public PageResource<Employee> getAllEmployees(Pageable pageable) throws URISyntaxException {
		log.debug("REST: solicitud para buscar Empleados Todo Paginado", pageable.toString());
		Page<Employee> pageResult = employeeService.getAllEmployees(pageable);	
		/*return new ResponseEntity<>(pageResult.getContent().stream()
	                .collect(Collectors.toList()),  HttpStatus.OK);*/
		
		return new PageResource<Employee>(pageResult,"page","size");
		 
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
	public ResponseEntity<Resource<EmployeeDTO>> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
		
		log.debug("REST: solicitud para salvar el Empleado: {}", employeeDTO);
		if (employeeDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert("employee", "idexists", "El nuevo Empleado no puede tener ID"))
					.body(null);
		}
		
		
		EmployeeDTO result = employeeMapper.employeeToEmployeeDTO(
				employeeService.saveEmployee(employeeMapper.employeeDTOToEmployee(employeeDTO)));
		employeeDTO.setId(result.getId());
		Resource<EmployeeDTO> resource = new Resource<EmployeeDTO>(result);
		resource.add(new Link("/api/employees/" + result.getId()));
		
		return ResponseEntity.created(new URI("/api/employees/" + result.getId())).body(resource);
	}

	@RequestMapping(value = "/employees", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource<EmployeeDTO>> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
		log.debug("REST: solicitud para modificar el Empleado: {}", employeeDTO);
		if (employeeDTO.getId() == null) {
			return createEmployee(employeeDTO);
		}
		EmployeeDTO result = employeeMapper.employeeToEmployeeDTO(
				employeeService.saveEmployee(employeeMapper.employeeDTOToEmployee(employeeDTO)));
		Resource<EmployeeDTO> resource = new Resource<EmployeeDTO>(result);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert("employee", result.getId().toString()))
				.body(resource);
	}

}
