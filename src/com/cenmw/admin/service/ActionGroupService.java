package com.cenmw.admin.service;

import java.util.List;
import java.util.Map;

import com.cenmw.admin.dao.ActionGroupDao;

public class ActionGroupService {
	private ActionGroupDao actionGroupDao;

	public List getActionGroupList(Map map) {
		return actionGroupDao.getActionGroupList(map);
	}

	public ActionGroupDao getActionGroupDao() {
		return actionGroupDao;
	}

	public void setActionGroupDao(ActionGroupDao actionGroupDao) {
		this.actionGroupDao = actionGroupDao;
	}

}
