package com.ssm.service;

import java.util.List;

import com.ssm.model.User;

public interface IUserService {

	public User getUserById(int id);

	public List<User> getAllUsers();
	
	public void insertUser(User user);

	public void updateUser(User user);

	public void deleteUser(int id);
}
