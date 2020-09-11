package com.howtodoinjava.demo.model;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement (name="employees")
public class EmployeeListVO
{
    private List<EmployeeVO> employees = new ArrayList<EmployeeVO>();
 
    public List<EmployeeVO> getEmployees() {
        return employees;
    }
 
    public void setEmployees(List<EmployeeVO> employees) {
        this.employees = employees;
    }
}