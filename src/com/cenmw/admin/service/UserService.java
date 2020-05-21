package com.cenmw.admin.service;

import com.cenmw.admin.dao.UserDao;
import com.cenmw.admin.po.User;

public class UserService {
	private UserDao userDao;

	public User getUser(String username, String password) {
		return userDao.getUser(username, password);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
