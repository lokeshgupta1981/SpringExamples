package com.springexamples.demo.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
  
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
public class Users extends ResourceSupport implements Serializable{
  
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="user")
    private ArrayList<User> users;
  
    public ArrayList<User> getUsers() {
        return users;
    }
  
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
