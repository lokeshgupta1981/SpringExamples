package com.howtodoinjava.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.rest.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee, Long> 
{
	 
}
