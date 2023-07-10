package com.howtodoinjava.hateoas.demo.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.hateoas.RepresentationModel;

@XmlRootElement(name = "employee-report")
public class EmployeeReportResult extends RepresentationModel<EmployeeReportResult> {

  // ...
}
