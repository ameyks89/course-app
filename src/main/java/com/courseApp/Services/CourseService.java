package com.courseApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseApp.Model.Course;
import com.courseApp.Model.User;
import com.courseApp.Repositories.CourseRepository;
import com.courseApp.Repositories.UserRepository;

@Service
public class CourseService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	//Create course for a user
	public Course saveCourse(Course course) throws Exception{
		User user = userRepository.findByUserName(course.getAuthor());
		course.getRegisteredUsers().add(user);
		return courseRepository.save(course);
	}

}
