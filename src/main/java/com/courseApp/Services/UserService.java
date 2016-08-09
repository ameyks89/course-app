package com.courseApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseApp.Exception.UserNotFoundException;
import com.courseApp.Model.User;
import com.courseApp.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// Create user
	public User save(User user) throws Exception {
		return userRepository.save(user);
	}

	// Delete user by name
	public void delete(String userName) throws Exception {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UserNotFoundException("User with name " + userName + " not found");
		} else {
			userRepository.delete(user);
		}
	}

	// Delete user by id
	public void delete(long userId) throws Exception {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new UserNotFoundException("User with Id " + userId + " not found");
		} else {
			userRepository.delete(user);
		}
	}

	// find user by id
	public User findUserbyId(Long userId) throws Exception {
		return userRepository.findOne(userId);
	}

	// find user by userName
	public User findUserbyName(String userName) throws Exception {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UserNotFoundException("User with name " + userName + " not found");
		} else {
			return userRepository.findByUserName(userName);
		}
	}
	
	//find all users
	public List<User> findAllUsers() throws Exception {
		return userRepository.findAll();
	}

	// Update user
	public User update(User userToUpdate) throws Exception {
		User userUpdate = userRepository.findByUserName(userToUpdate.getUserName());
		if (userUpdate == null) {
			throw new UserNotFoundException("User with username " + userToUpdate.getUserName() + " not found");
		} else {
			User updatedUser = userRepository.save(userToUpdate);
			return updatedUser;
		}
	}
}
