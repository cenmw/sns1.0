package com.cenmw.admin.manager.dao;

import java.util.List;
import java.util.Map;

import com.cenmw.admin.po.GroupManager;
import com.cenmw.base.BaseHibernateDao;

public class GroupManagerDao extends BaseHibernateDao {
	public void saveGroupManager(GroupManager gm) {
		save(gm);
	}

	public GroupManager getGroupManagerById(int id) {
		return (GroupManager) findObjectById(GroupManager.class, id);
	}

	public void deleteGroupManager(GroupManager gm) {
		delete(gm);
	}

	public void updateGroupManager(GroupManager gm) {
		updateObject(gm);
	}

	public List getGroupManagerList(Map map, String ordername, String sort) {
		return findList("GroupManager", map, ordername, sort);
	}
}
