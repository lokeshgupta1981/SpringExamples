package com.springexamples.demo.model;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
  
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
public class Users {
  
    @XmlElement(name="user")
    private ArrayList<User> users;
  
    public ArrayList<User> getUsers() {
        return users;
    }
  
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
