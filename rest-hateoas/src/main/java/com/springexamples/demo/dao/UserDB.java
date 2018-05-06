package com.springexamples.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.springexamples.demo.model.User;

public class UserDB {
	
	private static HashMap<Integer, User> DB = new HashMap<>(); 
	
	static {
		User one = new User(1, "Lokesh", "Gupta");
        User two = new User(2, "Amit", "Singhal");
        User three = new User(3, "Kirti", "Mishra");
 
        DB.put(1, one);
        DB.put(2, two);
        DB.put(3, three);
	}
	 
    public static ArrayList<User> getUsers()
    {
    	ArrayList<User> list = new ArrayList<>(DB.values());
        return list;
    }
    public static User getUserById( Integer id)
    {
        return DB.get(id);
    }
    public static void addUser(User user) {
    	DB.put(user.getUserId(), user);
    }
    
    public static void updateUser(User user) {
    	DB.put(user.getUserId(), user);
    }
    
    public static void removeUser(Integer id) {
    	DB.remove(id);
    }
}
