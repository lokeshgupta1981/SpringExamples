package com.springexamples.demo.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement(name="user-report")
public class Report extends ResourceSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Report() {
		super();
	}
	
	public Report(String data) {
		super();
		this.data = data;
	}

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
