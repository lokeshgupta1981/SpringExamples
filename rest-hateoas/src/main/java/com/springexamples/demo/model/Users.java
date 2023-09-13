package com.springexamples.demo.model;

import java.io.Serializable;
import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.RepresentationModel;

  
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
public class Users extends RepresentationModel implements Serializable{
  
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
