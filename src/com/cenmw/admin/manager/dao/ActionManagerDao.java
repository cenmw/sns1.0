package com.cenmw.admin.manager.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cenmw.admin.po.Action;
import com.cenmw.admin.po.ActionGroup;
import com.cenmw.base.BaseHibernateDao;
import com.cenmw.util.HqlBean;

public class ActionManagerDao extends BaseHibernateDao {
	public List getActionListNoAction(List alist){
		String hql="from Action order by id desc";
		if(alist!=null&&!alist.isEmpty()){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<alist.size();i++){
				ActionGroup a=(ActionGroup)alist.get(i);
				if(i>0){
					sb.append(",");
				}
				sb.append("'"+a.getAction()+"'");
			}
			hql="from Action where action not in ("+sb.toString()+") order by id desc";
		}
		return getListForHql(hql, null);
	}
	public List getActionListByAction(String action){
		String hql="from Action where action = :act";
		Map map=new HashMap();
		map.put("act", new HqlBean(action, "=", "and", "String"));
		return getListForHql(hql, map);
	}
	public List getActionList(Map map) {
		return findList("Action", map);
	}

	public List getActionList(Map map, String ordername, String sort) {
		return findList("Action", map, ordername, sort);
	}

	public Action getActionById(int id) {
		return (Action) findObjectById(Action.class, id);
	}

	public void saveAction(Action action) {
		save(action);
	}

	public void updateAction(Action action) {
		updateObject(action);
	}

	public void deleteAction(Action action) {
		delete(action);
	}
}
