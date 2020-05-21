package com.cenmw.admin.manager.service;

import java.util.List;
import java.util.Map;

import com.cenmw.admin.manager.dao.GroupManagerDao;
import com.cenmw.admin.po.GroupManager;

public class GroupManagerService {
	private GroupManagerDao groupManagerDao;

	public void saveGroupManager(GroupManager gm) {
		groupManagerDao.saveGroupManager(gm);
	}

	public void updateGroupManager(GroupManager gm) {
		groupManagerDao.updateGroupManager(gm);
	}

	public GroupManager getGroupManagerById(int id) {
		return groupManagerDao.getGroupManagerById(id);
	}

	public void deleteGroupManagerById(int id) {
		GroupManager gm = getGroupManagerById(id);
		if (gm != null) {
			groupManagerDao.deleteGroupManager(gm);
		}
	}

	public void deleteGroupManager(GroupManager gm) {
		groupManagerDao.deleteGroupManager(gm);
	}

	public List getGroupManagerList(Map map, String ordername, String sort) {
		return groupManagerDao.getGroupManagerList(map, ordername, sort);
	}

	public List getGroupManagerList(Map map) {
		return getGroupManagerList(map, "groupid", "asc");
	}

	public GroupManagerDao getGroupManagerDao() {
		return groupManagerDao;
	}

	public void setGroupManagerDao(GroupManagerDao groupManagerDao) {
		this.groupManagerDao = groupManagerDao;
	}

}
