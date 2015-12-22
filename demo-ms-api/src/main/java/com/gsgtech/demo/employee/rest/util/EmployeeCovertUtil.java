package com.gsgtech.demo.employee.rest.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.gsgtech.demo.employee.domain.Employee;
import com.gsgtech.demo.employee.dto.EmployeeDTO;

public class EmployeeCovertUtil {
	
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static EmployeeDTO convertToDto(Employee in) {	
		EmployeeDTO objReturn=null;
		if (in!=null) 
			objReturn =  modelMapper.map(in,EmployeeDTO.class);
		
		return objReturn;
	}
	
	
	public static Employee convertToEntity(EmployeeDTO in){
		Employee objReturn=null;
		if (in!=null){ 
			objReturn =  modelMapper.map(in,Employee.class);
		}
		return objReturn;
	}
	
	@SuppressWarnings("unchecked")
	public static List<EmployeeDTO> convertToListDto(List<Employee> lista){
		List<EmployeeDTO> dtos = new ArrayList<>();
		return modelMapper.map(lista, dtos.getClass());
	    
	     
	}
	

}
