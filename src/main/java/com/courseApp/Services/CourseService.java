package com.courseApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseApp.Model.Course;
import com.courseApp.Model.Topic;
import com.courseApp.Model.User;
import com.courseApp.Repositories.CourseRepository;
import com.courseApp.Repositories.TopicRepository;
import com.courseApp.Repositories.UserRepository;

@Service
public class CourseService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	//Create course for a user
	public Course saveCourse(Course course) throws Exception{
		User user = userRepository.findByUserName(course.getAuthor());
		course.getRegisteredUsers().add(user);
		return courseRepository.save(course);
	}
	
	//get course by Id
	public Course getCourseById(Long courseId) throws Exception{
		return courseRepository.findOne(courseId);
	}
	
	// Update user
	public Course update(Course courseToUpdate) throws Exception {
		Course courseUpdate = courseRepository.findOne(courseToUpdate.getCourseId());
		if (courseUpdate == null) {
			throw new Exception("Course Not found");
		} else {
			Course updatedCourse = courseRepository.save(courseToUpdate);
			return updatedCourse;
		}
	}
	
	//delete course by id
	public void deleteCourse(Long courseId) throws Exception{
		Course course = courseRepository.findOne(courseId);
		if(course == null){
			throw new Exception("Course Not found");
		}
		courseRepository.delete(course);
	}
	
	//Add user to course
	public Course addUserToCourse(Long userId,Long courseId) throws Exception{
		Course course = getCourseById(courseId);
		User user = userRepository.findOne(userId);
		course.getRegisteredUsers().add(user);
	    Course addedUser = courseRepository.save(course);
		return addedUser;
	}
	
	//Remove user from course
	public Course removeUserFromCourse(Long courseId, Long userId) throws Exception{
		Course course = getCourseById(courseId);
		User user = userRepository.findOne(userId);
		course.getRegisteredUsers().remove(user);
		Course removedUser = courseRepository.save(course);
		return removedUser;
	}
	
	//add topic to course
	public Course addTopicToCourse(Long courseId, Topic topic) throws Exception {
		Course course = getCourseById(courseId);
		topic.setCourse(course);
		course.getTopics().add(topic);
		Course addedTopic = courseRepository.save(course);
		return addedTopic;
	}
	
	//remove topic from course
	public Course removeTopicFromCourse(Long courseId, Long topicId) throws Exception {
		Course course = getCourseById(courseId);
		Topic topic=topicRepository.findOne(topicId);
		course.getTopics().remove(topic);
		Course removedTopic = courseRepository.save(course);
		return removedTopic;
	}
}
