package com.howtodoinjava.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.howtodoinjava.rest.controller.EmployeeController;
import com.howtodoinjava.rest.dao.EmployeeDAO;
import com.howtodoinjava.rest.model.Employee;
import com.howtodoinjava.rest.model.Employees;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest 
{
	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	EmployeeDAO employeeDAO;
	
	@Test
	public void testAddEmployee() 
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		when(employeeDAO.addEmployee(any(Employee.class))).thenReturn(true);
		
		Employee employee = new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
		ResponseEntity<Object> responseEntity = employeeController.addEmployee(employee);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
	}
	
	@Test
	public void testFindAll() 
	{
		// given
		Employee employee1 = new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
		Employee employee2 = new Employee(2, "Alex", "Gussin", "example@gmail.com");
		Employees employees = new Employees();
		employees.setEmployeeList(Arrays.asList(employee1, employee2));

		when(employeeDAO.getAllEmployees()).thenReturn(employees);

		// when
		Employees result = employeeController.getEmployees();

		// then
		assertThat(result.getEmployeeList().size()).isEqualTo(2);
		
		assertThat(result.getEmployeeList().get(0).getFirstName())
						.isEqualTo(employee1.getFirstName());
		
		assertThat(result.getEmployeeList().get(1).getFirstName())
						.isEqualTo(employee2.getFirstName());
	}
}
