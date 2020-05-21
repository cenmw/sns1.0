package com.cenmw.integral.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.integral.po.IntegralInfo;

public class IntegralInfoManagerDao extends BaseHibernateDao {

	public void saveIntegralInfo(IntegralInfo integralInfo) {
		save(integralInfo);
	}

	public void deleteIntegralInfo(IntegralInfo integralInfo) {
		delete(integralInfo);
	}

	public IntegralInfo getIntegralInfoById(int id) {
		return (IntegralInfo) findObjectById(IntegralInfo.class, id);
	}

	public void updateIntegralInfo(IntegralInfo integralInfo) {
		updateObject(integralInfo);
	}

}
