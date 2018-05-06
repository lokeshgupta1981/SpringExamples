package com.springexamples.demo.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
  
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
public class User extends ResourceSupport implements Serializable {
  
    private static final long serialVersionUID = 1L;
  
    @XmlAttribute(name = "userId")
    private int userId;
  
    @XmlElement(name = "firstName")
    private String firstName;
  
    @XmlElement(name = "lastName")
    private String lastName;
    
    public User(int id, String firstName, String lastName) {
		super();
		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
    
	public User() {
		super();
	}

    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
