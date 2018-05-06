package com.springexamples.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.springexamples.demo.dao.UserDB;
import com.springexamples.demo.model.User;
import com.springexamples.demo.model.Users;
 
@Path("/users")
public class UserResource
{
	@GET
    @Produces("application/json")
    public Users getAllUsers() {
        Users users = new Users();
        users.setUsers(UserDB.getUsers());
        
        /******Hateoas Links START**********/
        
        for (User user : users.getUsers()) 
        {
        	//Adding self link user 'singular' resource
            Link link = ControllerLinkBuilder
            		.linkTo(UserResource.class)
            		.slash(user.getUserId())
            		.withSelfRel();

            //Add link to singular resource
            user.add(link);
        }
        
        /******Hateoas Links END**********/
        
        return users;
    }
      
	@GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") int id) 
    {
        User user = UserDB.getUserById(id);
        if(user == null) {
            return Response.status(404).build();
        }
        
        /******Hateoas Links START**********/
        
      //Self link
        Link selfLink = ControllerLinkBuilder
        		.linkTo(UserResource.class)
        		.slash(user.getUserId())
        		.withSelfRel();
        
        user.add(selfLink);
        /******Hateoas Links END**********/
        
        return Response
                .status(200)
                .entity(user).build();
    }

}
