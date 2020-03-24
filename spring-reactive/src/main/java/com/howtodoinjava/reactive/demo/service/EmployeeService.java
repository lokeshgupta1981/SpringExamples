package com.howtodoinjava.reactive.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.howtodoinjava.reactive.demo.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService implements IEmployeeService 
{
	@Autowired
	WebClient webClient;
	
	public Flux<Employee> findAll() 
	{
		return webClient.get()
			.uri("/employees")
			.retrieve()
			.bodyToFlux(Employee.class);
	}

	public Mono<Employee> create(Employee empl)
	{
		return webClient.post()
				.uri("/employees")
				.body(Mono.just(empl), Employee.class)
				.retrieve()
				.bodyToMono(Employee.class);
	}

	public Mono<Employee> findById(Integer id) 
	{
		return webClient.get()
				.uri("/employees/" + id)
				.retrieve()
				/*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty())*/
				.bodyToMono(Employee.class);
	}

	public Mono<Employee> update(Employee e) 
	{
		return webClient.put()
				.uri("/employees/" + e.getId())
				.body(Mono.just(e), Employee.class)
				.retrieve()
				.bodyToMono(Employee.class);
	}

	public Mono<Void> delete(Integer id) 
	{
		return webClient.delete()
				.uri("/employees/" +id)
				.retrieve()
				.bodyToMono(Void.class);
	}

}