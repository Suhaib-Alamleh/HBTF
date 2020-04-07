package com.suhaib.repository;


import com.suhaib.entities.User;


public interface UserRepository {
	
	
	public User signup(User user);

	public User signin(String username, String password);
	
}
