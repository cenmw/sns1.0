package com.cenmw.admin.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.admin.manager.dao.ActionGroupManagerDao;
import com.cenmw.admin.manager.dao.UserManagerDao;
import com.cenmw.admin.po.ActionGroup;
import com.cenmw.admin.po.User;
import com.cenmw.util.HqlBean;
import com.cenmw.util.PageBean;

public class UserManagerService {
	private UserManagerDao userManagerDao;
	private ActionGroupManagerDao actionGroupManagerDao;
	public Map getUserActionMap(Map<String,Object> session,User user){
		Map actionMap=null;
		int groupid=user.getGroupid();
		List list=actionGroupManagerDao.getActionGroupListByGroupid(groupid);
		if(list!=null&&!list.isEmpty()){
			actionMap=new HashMap();
			for(int i=0;i<list.size();i++){
				ActionGroup a=(ActionGroup)list.get(i);
				actionMap.put(a.getAction(), a);
			}
		}
		return actionMap;
	}

	public boolean getUserByUsername(String username) {
		Map map = new HashMap();
		map.put("username", new HqlBean(username, "=", "and", "String"));
		List list = this.getUserList(map);
		if (list != null && !list.isEmpty()) {
			return false;
		}
		return true;
	}

	public void saveUser(User user) {
		userManagerDao.saveUser(user);
	}

	public boolean updateUser(User user) {
		User oloUser = getUserById(user.getId());
		Map map = new HashMap();
		map.put("username", new HqlBean(user.getUsername(), "=", "and",
				"String"));
		List list = getUserList(map);
		User newUser = null;
		if (list != null && !list.isEmpty()) {
			newUser = (User) list.get(0);
		}
		if (newUser == null) {
			userManagerDao.updateUser(user);
			return true;
		} else {
			if (user.getUsername().equals(oloUser.getUsername())) {
				userManagerDao.updateUser(user);
				return true;
			}
		}
		return false;
	}

	public User getUserById(int id) {
		return userManagerDao.getUserById(id);
	}

	public void deleteUserById(int id) {
		userManagerDao.deleteUserById(id);
	}

	public List getUserList(Map map, String ordername, String sort) {
		return userManagerDao.getUserList(map, ordername, sort);
	}

	public List getUserList(Map map) {
		return userManagerDao.getUserList(map, "id", "desc");
	}

	public PageBean getUserForPage(Map map, String ordername, String sort,
			int cpage, int pageSize) {
		return userManagerDao.getUserForPage(map, ordername, sort, cpage,
				pageSize);
	}

	public PageBean getUserForPage(Map map, int cpage, int pageSize) {
		return userManagerDao
				.getUserForPage(map, "id", "desc", cpage, pageSize);
	}

	public UserManagerDao getUserManagerDao() {
		return userManagerDao;
	}

	public void setUserManagerDao(UserManagerDao userManagerDao) {
		this.userManagerDao = userManagerDao;
	}

	public ActionGroupManagerDao getActionGroupManagerDao() {
		return actionGroupManagerDao;
	}

	public void setActionGroupManagerDao(ActionGroupManagerDao actionGroupManagerDao) {
		this.actionGroupManagerDao = actionGroupManagerDao;
	}
	
}
