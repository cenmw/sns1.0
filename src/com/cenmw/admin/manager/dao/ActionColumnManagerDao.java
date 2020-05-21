package com.cenmw.admin.manager.dao;

import java.util.List;
import java.util.Map;

import com.cenmw.admin.po.ActionColumn;
import com.cenmw.base.BaseHibernateDao;

public class ActionColumnManagerDao extends BaseHibernateDao {
	public List getActionColumnList(Map map, String ordername, String sort) {
		return findList("ActionColumn", map, ordername, sort);
	}

	public ActionColumn getActionColumnById(int acid) {
		return (ActionColumn) findObjectById(ActionColumn.class, acid);
	}

	public void deleteActionColumn(int acid) {
		ActionColumn ac = getActionColumnById(acid);
		if (ac != null) {
			delete(ac);
		}
	}

	public void deleteActionColumn(ActionColumn actionColumn) {
		delete(actionColumn);
	}

	public void saveActionColumn(ActionColumn actionColumn) {
		save(actionColumn);
	}

	public void updateActionColumn(ActionColumn actionColumn) {
		updateObject(actionColumn);
	}
}
