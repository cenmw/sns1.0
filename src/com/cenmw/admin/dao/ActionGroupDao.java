package com.cenmw.admin.dao;

import java.util.List;
import java.util.Map;

import com.cenmw.base.BaseHibernateDao;

public class ActionGroupDao extends BaseHibernateDao {
	public List getActionGroupList(Map map) {
		return findList("ActionGroup", map);
	}
}
