package com.BlogApp.services;

import java.util.List;

import com.BlogApp.entities.User;
public interface UserService {
	User createUser(User user);
	User updateUserByUsername(User user,String username);
	User getUserByUsername(String username);
	boolean deleteUserByUsername(String username);
	boolean deleteAllUsers();
	List<User> getAllUsers();
}
