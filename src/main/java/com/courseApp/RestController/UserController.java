package com.courseApp.RestController;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.courseApp.Model.Course;
import com.courseApp.Model.User;
import com.courseApp.Services.UserService;
//Steps to create validations By Appi
//first apply @valid annotation for the object to be validated
//go to domain class apply validation annotations like not null,past,length, pattern,email
//create a custom class which tells client what the errors are
//you need to know what exception spring throws when validation fails
//for that exception we need to define an exception handler which goes to controller advice class

@Component
@Produces("application/json")
@Path("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Create User
	@POST
	@Consumes("application/json")
	@Path("/create")
	public ResponseEntity<User> create(User user) throws Exception{
		return new ResponseEntity<User>(userService.save(user),HttpStatus.CREATED);
	}
	
	
	//Delete user by user name
	@DELETE
	@Path("/deleteByName")
	public ResponseEntity<HttpStatus> deleteByName(@QueryParam(value = "userName")String userName) throws Exception{
		userService.delete(userName);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	//delete user by user id
	@DELETE
	@Path("/deleteById")
	public ResponseEntity<HttpStatus> deleteById(@QueryParam(value = "userId")Long userId) throws Exception{
		userService.delete(userId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	//update a user
	@PUT
	@Path("/UpdateByUser")
	public ResponseEntity<User> update(User userToUpdate) throws Exception{
		return new ResponseEntity<User>(userService.update(userToUpdate),HttpStatus.CREATED);
	}
	
	
	//find user by id
	@GET
	@Path("/findUserById")
	public ResponseEntity<User> findUserByName(@QueryParam(value = "userId")Long userId) throws Exception{
		return new ResponseEntity<User>(userService.findUserbyId(userId),HttpStatus.OK);
	}
	
	// find user by name
	@GET
	@Path("/findUserByName")
	public ResponseEntity<User> findUserByName(@QueryParam(value = "userName")String userName) throws Exception{
		return new ResponseEntity<User>(userService.findUserbyName(userName),HttpStatus.OK);
	}
	
	//find all users
	@GET
	@Path("/findAllUser")
	public ResponseEntity<List<User>> findAllUser() throws Exception{
		return new ResponseEntity<List<User>>(userService.findAllUsers(),HttpStatus.OK);
	}
	
	//Get all course for a user by his id
	@GET
	@Path("/courses/{userId}")
	public ResponseEntity<List<Course>> findCoursesForUser(@PathParam(value = "userId")Long userId) throws Exception{
		return new ResponseEntity<List<Course>>(userService.findUserbyId(userId).getCourses(),HttpStatus.OK);
	}
}
