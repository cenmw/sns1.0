package com.cenmw.labor.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.labor.po.LaborClass;

public class LaborClassFrontDao extends BaseHibernateDao {

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
