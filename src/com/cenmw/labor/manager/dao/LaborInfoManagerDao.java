package com.cenmw.labor.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.labor.po.LaborInfo;

public class LaborInfoManagerDao extends BaseHibernateDao {

	public void saveLaborInfo(LaborInfo laborInfo) {
		save(laborInfo);
	}

	public void deleteLaborInfo(LaborInfo laborInfo) {
		delete(laborInfo);
	}

	public LaborInfo getLaborInfoById(int id) {
		return (LaborInfo) findObjectById(LaborInfo.class, id);
	}

	public void updateLaborInfo(LaborInfo laborInfo) {
		updateObject(laborInfo);
	}

}
