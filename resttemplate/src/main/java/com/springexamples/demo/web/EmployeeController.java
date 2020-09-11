package com.springexamples.demo.web;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springexamples.demo.model.EmployeeListVO;
import com.springexamples.demo.model.EmployeeVO;

@RestController
public class EmployeeController 
{
	private static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class); 
	
	@GetMapping(value = "/employees", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public EmployeeListVO getAllEmployees(
    		@RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
            @RequestHeader(name = "X-COM-LOCATION", defaultValue = "ASIA") String headerLocation) 
    {
		LOGGER.info("Header X-COM-PERSIST :: " + headerPersist);
		LOGGER.info("Header X-COM-LOCATION :: " + headerLocation);
		
        EmployeeListVO employees = getEmployeeList();
        return employees;
    }
     
	@GetMapping(value = "/employees/{id}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeeVO> getEmployeeById (@PathVariable("id") Integer id) 
    {
    	LOGGER.info("Requested employee id :: " + id);
    	
        if (id != null && id > 0) {
        	//TODO: Fetch the employee and return from here
            EmployeeVO employee = new EmployeeVO(id, "Lokesh","Gupta", "howtodoinjava@gmail.com");
            return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<EmployeeVO>(HttpStatus.NOT_FOUND);
    }
	
	@PostMapping(value = "/employees")
	public ResponseEntity<String> createEmployee(EmployeeVO employee) 
	{
		//TODO: Save employee details which will generate the employee id
		employee.setId(111);
		
		//Build URI
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			                 .path("/{id}")
			                 .buildAndExpand(employee.getId())
			                 .toUri();
	    return ResponseEntity.created(location).build();
	}
    
	@PutMapping(value = "/employees/{id}")
	public ResponseEntity<EmployeeVO> updateEmployee(@PathVariable("id") int id
									,EmployeeVO employee) 
	{
		//TODO: Save employee details
	    return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) 
	{
		//TODO: Delete the employee record
	    return new ResponseEntity<String>(HttpStatus.OK);
	}
    
    
	//TODO: Making it static to persist data - for demo purpose
  	static EmployeeListVO employees = new EmployeeListVO();
  	
    //TODO: Hardcoded data to replace
    private EmployeeListVO getEmployeeList() 
    {
    	EmployeeListVO employees = new EmployeeListVO();
    	
    	EmployeeVO empOne = new EmployeeVO(1,"Lokesh","Gupta","howtodoinjava@gmail.com");
        EmployeeVO empTwo = new EmployeeVO(2,"Amit","Singhal","asinghal@yahoo.com");
        EmployeeVO empThree = new EmployeeVO(3,"Kirti","Mishra","kmishra@gmail.com");
        
        employees.getEmployees().add(empOne);
        employees.getEmployees().add(empTwo);
        employees.getEmployees().add(empThree);
        
        return employees;
    }
}
