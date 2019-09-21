package com.howtodoinjava.demo;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.howtodoinjava.demo.controller.EmployeeController;
import com.howtodoinjava.demo.dao.EmployeeRepository;
import com.howtodoinjava.demo.model.Employee;
import com.howtodoinjava.demo.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = EmployeeController.class)
@Import(EmployeeService.class)
public class EmployeeControllerTest 
{
	@MockBean
	EmployeeRepository repository;

	@Autowired
	private WebTestClient webClient;

	@Test
	void testCreateEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Test");
		employee.setSalary(1000);

		Mockito.when(repository.save(employee)).thenReturn(Mono.just(employee));

		webClient.post()
			.uri("/create")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(employee))
			.exchange()
			.expectStatus().isCreated();

		Mockito.verify(repository, times(1)).save(employee);
	}
	
	@Test
    void testGetEmployeesByName() 
	{
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Test");
		employee.setSalary(1000);
		
		List<Employee> list = new ArrayList<Employee>();
		list.add(employee);
		
		Flux<Employee> employeeFlux = Flux.fromIterable(list);
		
        Mockito
            .when(repository.findByName("Test"))
            .thenReturn(employeeFlux);

        webClient.get().uri("/name/{name}", "Test")
        	.header(HttpHeaders.ACCEPT, "application/json")
	        .exchange()
	        .expectStatus().isOk()
	        .expectBodyList(Employee.class);
        
        Mockito.verify(repository, times(1)).findByName("Test");
    }
	
	@Test
    void testGetEmployeeById() 
	{
		Employee employee = new Employee();
		employee.setId(100);
		employee.setName("Test");
		employee.setSalary(1000);
			
        Mockito
            .when(repository.findById(100))
            .thenReturn(Mono.just(employee));

        webClient.get().uri("/{id}", 100)
	        .exchange()
	        .expectStatus().isOk()
	        .expectBody()
	        .jsonPath("$.name").isNotEmpty()
	        .jsonPath("$.id").isEqualTo(100)
	        .jsonPath("$.name").isEqualTo("Test")
	        .jsonPath("$.salary").isEqualTo(1000);
        
        Mockito.verify(repository, times(1)).findById(100);
    }

	@Test
    void testDeleteEmployee() 
	{
		Mono<Void> voidReturn  = Mono.empty();
        Mockito
            .when(repository.deleteById(1))
            .thenReturn(voidReturn);

        webClient.get().uri("/delete/{id}", 1)
	        .exchange()
	        .expectStatus().isOk();
    }
}
