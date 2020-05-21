package com.cenmw.admin.manager.service;

import java.util.List;

import com.cenmw.admin.manager.dao.ActionColumnManagerDao;
import com.cenmw.admin.po.ActionColumn;

public class ActionColumnManagerService {
	private ActionColumnManagerDao actionColumnManagerDao;

	public ActionColumn getActionColumnById(int acid) {
		return actionColumnManagerDao.getActionColumnById(acid);
	}

	public void saveActionColumn(ActionColumn actionColumn) {
		actionColumnManagerDao.saveActionColumn(actionColumn);
	}

	public void updateActionColumn(ActionColumn actionColumn) {
		actionColumnManagerDao.updateActionColumn(actionColumn);
	}

	public void deleteActionColumn(int acid) {
		actionColumnManagerDao.deleteActionColumn(acid);
	}

	public List getActionColumnList() {
		String ordername = "sort";
		String sort = "asc";
		return actionColumnManagerDao
				.getActionColumnList(null, ordername, sort);
	}

	public ActionColumnManagerDao getActionColumnManagerDao() {
		return actionColumnManagerDao;
	}

	public void setActionColumnManagerDao(
			ActionColumnManagerDao actionColumnManagerDao) {
		this.actionColumnManagerDao = actionColumnManagerDao;
	}

}
