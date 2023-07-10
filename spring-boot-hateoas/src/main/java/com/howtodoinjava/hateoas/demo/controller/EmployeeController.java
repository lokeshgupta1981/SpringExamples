package com.howtodoinjava.hateoas.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.howtodoinjava.hateoas.demo.dao.EmployeeRepository;
import com.howtodoinjava.hateoas.demo.model.EmployeeList;
import com.howtodoinjava.hateoas.demo.model.EmployeeReportResult;
import com.howtodoinjava.hateoas.demo.model.Employee;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired
  EmployeeRepository repository;

  @GetMapping("/employees")
  public EmployeeList getAllEmployees() {
    EmployeeList employeesList = new EmployeeList();

    for (Employee employee : repository.findAll()) {

      addLinkToEmployee(employee);
      employeesList.getEmployees().add(employee);
    }

    // Adding self link employee collection resource
    Link selfLink = linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel();
    employeesList.add(selfLink);

    return employeesList;
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployeeById(
      @PathVariable("id") int id) {

    Optional<Employee> employeeOpt = repository.findById(id);

    if (employeeOpt.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Employee employee = employeeOpt.get();
    addLinkToEmployee(employee);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }

  @GetMapping("/employees/{id}/report")
  public ResponseEntity<EmployeeReportResult> getReportByEmployeeById(
      @PathVariable("id") int id) {
    // Do some operation and return report
    return null;
  }

  private void addLinkToEmployee(Employee employee) {

    // Adding self link employee 'singular' resource
    Link link = linkTo(EmployeeController.class).slash(employee.getId()).withSelfRel();
    employee.add(link);

    // Adding method link employee 'singular' resource
    ResponseEntity<EmployeeReportResult> methodLinkBuilder =
        methodOn(EmployeeController.class).getReportByEmployeeById(employee.getId());
    Link reportLink = linkTo(methodLinkBuilder).withRel("employee-report");
    employee.add(reportLink);
  }
}
