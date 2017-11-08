package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.IUserDao;
import com.ssm.model.User;
import com.ssm.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;

	@Override
	public User getUserById(int id) {
		return this.userDao.getUserById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userDao.getAllUsers();
	}

	@Override
	public void insertUser(User user) {
		this.userDao.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}

	@Override
	public void deleteUser(int id) {
		this.userDao.deleteUser(id);
	}

}
