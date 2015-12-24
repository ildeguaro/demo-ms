package com.gsgtech.demo.employee.rest;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsgtech.demo.employee.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class EmployeeResourceTest {

  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Logger log = LoggerFactory.getLogger(EmployeeResourceTest.class);
   
    private RestTemplate restTemplate = new TestRestTemplate();
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllEmployeesTest() {
		log.trace("Testing getAllEmployeesTest"); 
		Map<String, Object> apiResponse = restTemplate.getForObject("http://localhost:8080/employees/", Map.class);
		 
		int total = Integer.parseInt(apiResponse.get("totalElements").toString());
		assertTrue(total > 1);

				
		log.trace("Finish getAllEmployeesTest"); 
		 
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getEmployeeByFirstNameTest() {
		log.trace("Testing getEmployeeByFirstNameTest"); 
		Map<String, Object> apiResponse = restTemplate.getForObject("http://localhost:8080/employees/search/aldemar/1111", Map.class);
		
		assertNotNull(apiResponse);	
		
		log.trace("Finish getEmployeeByFirstNameTest"); 
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void createEmployeeTest() throws JsonProcessingException {
	  log.trace("Testing createEmployeeTest"); 
	  Map<String, Object> requestBody = new HashMap<String, Object>();
	  requestBody.put("firstName", "1111");
	  requestBody.put("lastName", "2222");
	  requestBody.put("birthDate", "01/01/1900");
	  requestBody.put("email", "email@com.com");
	  requestBody.put("numPhone", "+58009874500");
	  HttpHeaders requestHeaders = new HttpHeaders();
	  requestHeaders.setContentType(MediaType.APPLICATION_JSON);

	 
	  HttpEntity<String> httpEntity =
       new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

	 
	  Map<String, Object> apiResponse =
	   restTemplate.postForObject("http://localhost:8080/employees", 
			  httpEntity, Map.class, Collections.EMPTY_MAP);
	  assertNotNull(apiResponse);		 
	  String id = ((Map<String, Object>)apiResponse.get("id")).toString();
	  assertNotNull(id);
	  log.trace("Finish createEmployeeTest"); 
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deleteEmployeeTest() {
		log.trace("Testing deleteEmployeeTest"); 
		restTemplate.delete("http://localhost:8080/employees/1", Collections.EMPTY_MAP);
		//Desconocimiento de assert para esta verbo
		//assertNotNull(apiResponse);	
		log.trace("Finish deleteEmployeeTest"); 
	}
	

}
