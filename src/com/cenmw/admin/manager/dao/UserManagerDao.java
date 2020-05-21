package com.cenmw.admin.manager.dao;

import java.util.List;
import java.util.Map;

import com.cenmw.admin.po.User;
import com.cenmw.base.BaseHibernateDao;
import com.cenmw.util.PageBean;

public class UserManagerDao extends BaseHibernateDao {
	public void saveUser(User user) {
		save(user);
	}

	public void updateUser(User user) {
		updateObject(user);
	}

	public void deleteUser(User user) {
		delete(user);
	}

	public User getUserById(int id) {
		return (User) findObjectById(User.class, id);
	}

	public void deleteUserById(int id) {
		User user = (User) findObjectById(User.class, id);
		if (user != null) {
			delete(user);
		}
	}

	public List getUserList(Map map, String ordername, String sort) {
		return findList("User", map, ordername, sort);
	}

	public PageBean getUserForPage(Map map, String ordername, String sort,
			int cpage, int pageSize) {
		return findListForPage("User", map, ordername, sort, cpage, pageSize);
	}

}
