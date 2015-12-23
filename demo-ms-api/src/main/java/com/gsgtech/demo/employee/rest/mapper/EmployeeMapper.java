package com.gsgtech.demo.employee.rest.mapper;

import com.gsgtech.demo.employee.domain.Employee;
import com.gsgtech.demo.employee.rest.dto.EmployeeDTO;


import org.mapstruct.*;

/**
 * Mapper para la entidad Employee y su DTO EmployeeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeeMapper {

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);
}
