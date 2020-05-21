package com.cenmw.admin.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.admin.manager.dao.ActionGroupManagerDao;
import com.cenmw.admin.manager.dao.ActionManagerDao;
import com.cenmw.admin.po.Action;
import com.cenmw.util.HqlBean;

public class ActionManagerService {
	private ActionManagerDao actionManagerDao;
	private ActionGroupManagerDao actionGroupManagerDao;

	public void saveAction(Action action) {
		actionManagerDao.saveAction(action);
	}

	public List getActionListByAction(String action) {
		return actionManagerDao.getActionListByAction(action);
	}

	public void updateAction(Action action) {
		actionManagerDao.updateAction(action);
	}

	public void deleteAction(Action action) {
		actionGroupManagerDao.deleteActionGroupByAction(action.getAction());
		actionManagerDao.deleteAction(action);
	}

	public List getActionList(Map map) {
		return actionManagerDao.getActionList(map, "id", "asc");
	}

	public List getActionList(Map map, String ordername, String sort) {
		return actionManagerDao.getActionList(map, ordername, sort);
	}

	public Action getActionById(int id) {
		return actionManagerDao.getActionById(id);
	}

	public ActionManagerDao getActionManagerDao() {
		return actionManagerDao;
	}

	public void setActionManagerDao(ActionManagerDao actionManagerDao) {
		this.actionManagerDao = actionManagerDao;
	}

	public ActionGroupManagerDao getActionGroupManagerDao() {
		return actionGroupManagerDao;
	}

	public void setActionGroupManagerDao(
			ActionGroupManagerDao actionGroupManagerDao) {
		this.actionGroupManagerDao = actionGroupManagerDao;
	}

}
