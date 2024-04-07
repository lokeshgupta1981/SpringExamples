package com.howtodoinjava.rest;

import com.howtodoinjava.rest.model.Employee;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = SpringBootDemoApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTemplatePostApiExamples {

  @LocalServerPort
  int randomServerPort;

  @Test
  public void testAddEmployee_withBody_thenSuccess() throws URISyntaxException {
    RestTemplate restTemplate = new RestTemplate();
    URI uri = new URI("http://localhost:" + randomServerPort + "/employees/");

    Employee employee = new Employee("Adam", "Gilly", "test@email.com");

    Employee createdEmployee = restTemplate.postForObject(uri, employee, Employee.class);

    Assertions.assertNotNull(createdEmployee.getId());
  }

  @Test
  public void testAddEmployeeWithoutHeader_success() throws URISyntaxException
  {
    RestTemplate restTemplate = new RestTemplate();

    URI uri = new URI("http://localhost:"+randomServerPort+"/employees/");
    Employee employee = new Employee("Adam", "Gilly", "test@email.com");

    HttpHeaders headers = new HttpHeaders();
    headers.set("X-COM-PERSIST", "true");
    headers.set("X-COM-LOCATION", "USA");

    HttpEntity<Employee> httpEntity = new HttpEntity<>(employee, headers);

    ResponseEntity<String> result = restTemplate.postForEntity(uri, httpEntity, String.class);

    Assertions.assertEquals(201, result.getStatusCode().value());
  }

  @Test
  public void testAddEmployeeWithoutHeader_fail() throws URISyntaxException
  {
    RestTemplate restTemplate = new RestTemplate();

    URI uri = new URI("http://localhost:"+randomServerPort+"/employees/");
    Employee employee = new Employee("Adam", "Gilly", "test@email.com");

    HttpHeaders headers = new HttpHeaders();
    headers.set("X-COM-LOCATION", "USA");
    HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

    ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

    Assertions.assertEquals(400, result.getStatusCode().value());
  }
}
