package com.howtodoinjava.reactive.demo.service;

import com.howtodoinjava.reactive.demo.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface IEmployeeService
{
	Flux<Employee> findAll();
	
	Mono<Employee> findById(Integer id);
 
    Mono<Employee> create(Employee e);
 
    Mono<Employee> update(Employee e);
 
    Mono<Void> delete(Integer id);
}