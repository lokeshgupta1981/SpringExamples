package com.howtodoinjava.reactive.demo.model;

import java.io.Serializable;
 
public class Employee implements Serializable 
{ 
	private static final long serialVersionUID = 1L;
	
	long id;
    String name;
    String status;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
}
