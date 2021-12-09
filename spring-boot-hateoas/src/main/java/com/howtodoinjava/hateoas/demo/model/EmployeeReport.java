package com.howtodoinjava.hateoas.demo.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.hateoas.RepresentationModel;

@XmlRootElement(name = "employee-report")
public class EmployeeReport extends RepresentationModel<EmployeeReport>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    // You can add field as needed
}
