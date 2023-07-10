package com.howtodoinjava.hateoas.demo.dao;

import com.howtodoinjava.hateoas.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
