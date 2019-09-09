package com.howtodoinjava.rest.dao;

import org.springframework.stereotype.Repository;

import com.howtodoinjava.rest.model.Employee;
import com.howtodoinjava.rest.model.Employees;

@Repository
public class EmployeeDAO 
{
    private static Employees list = new Employees();
    
    public Employees getAllEmployees() 
    {
        return list;
    }
    
    public boolean addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
        return true;
    }
}
