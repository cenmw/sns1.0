package com.cenmw.admin.manager.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.admin.po.ActionGroup;
import com.cenmw.base.BaseHibernateDao;
import com.cenmw.util.HqlBean;
import com.cenmw.util.PageBean;

public class ActionGroupManagerDao extends BaseHibernateDao {
	public List getActionListByGroupid(int groupid) {
		String hql = "from ActionGroup where groupid=:groupid order by id desc";
		Map map = new HashMap();
		map.put("groupid", new HqlBean(groupid, "=", "and", "Integer"));
		return getListForHql(hql, map);
	}

	public PageBean getActionByGroupid(int groupid, int currentPage,
			int pageSize) {
		String hql = "from ActionGroup where groupid=:groupid";
		Map map = new HashMap();
		map.put("groupid", new HqlBean(groupid, "=", "and", "Integer"));
		return findListHqlForPage(hql, "id desc", map, currentPage, pageSize);
	}

	public void saveActionGroup(ActionGroup actionGroup) {
		save(actionGroup);
	}

	public void updateActionGroup(ActionGroup actionGroup) {
		updateObject(actionGroup);
	}

	public ActionGroup getActionGroupById(int id) {
		return (ActionGroup) findObjectById(ActionGroup.class, id);
	}

	public void deleteActionGroup(ActionGroup actionGroup) {
		delete(actionGroup);
	}

	public void deleteActionGroupById(int id) {
		ActionGroup actionGroup = getActionGroupById(id);
		if (actionGroup != null) {
			deleteActionGroup(actionGroup);
		}
	}

	public List getActionGroupListByAction(String action) {
		String hql = "from ActionGroup where action=:action";
		Map map = new HashMap();
		map.put("action", new HqlBean(action, "String"));
		return getListForHql(hql, map);
	}

	public void deleteActionGroupByAction(String action) {
		List list = getActionGroupListByAction(action);
		if (list != null && !list.isEmpty()) {
			for (Object obj : list) {
				ActionGroup a = (ActionGroup) obj;
				deleteActionGroup(a);
			}
		}
	}

	public List getActionGroupListByGroupid(int groupid) {
		String hql = "from ActionGroup where groupid=:groupid";
		HqlBean hb = new HqlBean(groupid, "Integer");
		Map map = new HashMap();
		map.put("groupid", hb);
		return getListForHql(hql, map);
	}

	public List getActionGroupList(Map map, String ordername, String sort) {
		return findList("ActionGroup", map, ordername, sort);
	}

	public PageBean getActionGroupForPage(Map map, String ordername,
			String sort, int cpage, int pageSize) {
		return findListForPage("ActionGroup", map, ordername, sort, cpage,
				pageSize);
	}
}
