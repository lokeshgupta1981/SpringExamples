package com.howtodoinjava.hateoas.demo;

import com.howtodoinjava.hateoas.demo.dao.EmployeeRepository;
import com.howtodoinjava.hateoas.demo.model.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
  }

  @Autowired
  EmployeeRepository repository;

  @Override
  public void run(String... args) throws Exception {
    repository.saveAll(List.of(
        new Employee(null, "Alex", "Dave", "alex@gmail.com"),
        new Employee(null, "Brian", "Dave", "brian@gmail.com"),
        new Employee(null, "Charles", "Dave", "charles@gmail.com")));
  }
}
