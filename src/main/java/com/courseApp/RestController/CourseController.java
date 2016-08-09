package com.courseApp.RestController;

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
import com.courseApp.Model.Topic;
import com.courseApp.Services.CourseService;


@Component
@Produces("application/json")
@Path("course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
		//Create User
		@POST
		@Consumes("application/json")
		@Path("/createCourse")
		public ResponseEntity<Course> create(Course course) throws Exception{
			return new ResponseEntity<Course>(courseService.saveCourse(course),HttpStatus.CREATED);
		}
		
		//Get course by id
		@GET
		@Produces("application/json")
		@Path("/getCourseById")
		public ResponseEntity<Course> getCourseById(@QueryParam(value = "courseId") Long courseId) throws Exception{
			return new ResponseEntity<Course>(courseService.getCourseById(courseId),HttpStatus.OK);
		}
		
		//to update course
		@PUT
		@Path("/updateCourse")
		public ResponseEntity<Course> update(Course courseToUpdate) throws Exception {
			return new ResponseEntity<Course>(courseService.update(courseToUpdate), HttpStatus.OK);
		}
		
		//delete course by id
		@DELETE
		@Path("/deleteCourseById")
		public ResponseEntity<HttpStatus> delete(@QueryParam(value="courseId") Long courseId) throws Exception {
			courseService.deleteCourse(courseId);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		//add user to course
		@PUT
		@Consumes("application/json")
		@Path("{courseId}/users/{userId}")
		public ResponseEntity<Course> addUserToCourse(@PathParam(value="courseId") Long courseId, @PathParam(value="userId")Long userId) throws Exception{
			return new ResponseEntity<Course>(courseService.addUserToCourse(userId, courseId),HttpStatus.CREATED);
		}
		
		//delete user from course
		@DELETE
		@Path("{courseId}/deleteUsers/{userId}")
		public ResponseEntity<Course> removeUserFromCourse(@PathParam("courseId")Long courseId, @PathParam("userId")Long userId) throws Exception {
			return new ResponseEntity<Course>(courseService.removeUserFromCourse(courseId, userId), HttpStatus.OK);
		}
		
		//add Topic to a course
		@PUT
		@Consumes("application/json")
		@Path("{courseId}/topic")
		public ResponseEntity<Course> addTopicToCourse(@PathParam("courseId")Long courseId, Topic topic) throws Exception {
			
			return new ResponseEntity<Course>(courseService.addTopicToCourse(courseId, topic), HttpStatus.OK);
		}
		
		//remove Topic from a course
		@DELETE
		@Path("{courseId}/deleteTopic/{topicId}")
		public ResponseEntity<Course> removeTopicFromACourse(@PathParam("courseId")Long courseId,@PathParam("topicId") Long topicId) throws Exception {
			
			return new ResponseEntity<Course>(courseService.removeTopicFromCourse(courseId, topicId), HttpStatus.OK);
		}

}
