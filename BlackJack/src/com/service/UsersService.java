package com.service;

import com.dao.UsersDao;
import com.entity.Users;

public class UsersService {
	private UsersDao usersDao=new UsersDao();
	public Users selectByName(String name) {
		return usersDao.selectByName(name);
	}
	public int addUsers(Users users) {
		return usersDao.addUsers(users);
	}
	public void updateUsers(Users users) {
		usersDao.updateUsers(users);
	}
}
