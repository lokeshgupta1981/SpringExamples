package com.howtodoinjava.hateoas.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "employee")
public class Employee extends RepresentationModel<Employee> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
}
