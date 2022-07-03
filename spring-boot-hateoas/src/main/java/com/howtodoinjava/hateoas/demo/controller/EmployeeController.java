package com.howtodoinjava.hateoas.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.howtodoinjava.hateoas.demo.dao.EmployeeDB;
import com.howtodoinjava.hateoas.demo.model.EmployeeListVO;
import com.howtodoinjava.hateoas.demo.model.EmployeeReport;
import com.howtodoinjava.hateoas.demo.model.EmployeeVO;

@RestController
public class EmployeeController {

    @GetMapping("/employees")
    public EmployeeListVO getAllEmployees() {
        EmployeeListVO employeesList = new EmployeeListVO();

        for (EmployeeVO employee : EmployeeDB.getEmployeeList()) {
            // Adding self link employee 'singular' resource
            Link link = linkTo(EmployeeController.class)
                    .slash(employee.getEmployeeId()).withSelfRel();

            // Add link to singular resource
            employee.add(link);

            // Adding method link employee 'singular' resource
            ResponseEntity<EmployeeReport> methodLinkBuilder =
                    methodOn(EmployeeController.class)
                            .getReportByEmployeeById(employee.getEmployeeId());
            Link reportLink =
                    linkTo(methodLinkBuilder).withRel("employee-report");

            // Add link to singular resource
            employee.add(reportLink);

            employeesList.getEmployees().add(employee);
        }

        // Adding self link employee collection resource
        Link selfLink =
                linkTo(methodOn(EmployeeController.class).getAllEmployees())
                        .withSelfRel();

        // Add link to collection resource
        employeesList.add(selfLink);

        return employeesList;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeVO> getEmployeeById(
            @PathVariable("id") int id) {
        if (id <= 3) {
            EmployeeVO employee = EmployeeDB.getEmployeeList().get(id - 1);

            // Self link
            Link selfLink = linkTo(EmployeeController.class)
                    .slash(employee.getEmployeeId()).withSelfRel();

            // Method link
            Link reportLink = linkTo(methodOn(EmployeeController.class)
                    .getReportByEmployeeById(employee.getEmployeeId()))
                            .withRel("report");

            employee.add(selfLink);
            employee.add(reportLink);
            return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<EmployeeVO>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees/{id}/report")
    public ResponseEntity<EmployeeReport> getReportByEmployeeById(
            @PathVariable("id") int id) {
        // Do some operation and return report
        return null;
    }
}
