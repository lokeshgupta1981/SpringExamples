package com.howtodoinjava.hateoas.demo.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@XmlRootElement(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeList extends RepresentationModel<EmployeeList> {

  private List<Employee> employees = new ArrayList<Employee>();
}
