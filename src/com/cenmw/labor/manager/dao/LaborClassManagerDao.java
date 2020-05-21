package com.cenmw.labor.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.labor.po.LaborClass;

public class LaborClassManagerDao extends BaseHibernateDao {

	public void saveLaborClass(LaborClass laborClass) {
		save(laborClass);
	}

	public void deleteLaborClass(LaborClass laborClass) {
		delete(laborClass);
	}

	public LaborClass getLaborClassById(int id) {
		return (LaborClass) findObjectById(LaborClass.class, id);
	}

	public void updateLaborClass(LaborClass laborClass) {
		updateObject(laborClass);
	}

}
