package com.cenmw.integral.front.dao;

import java.util.List;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.integral.po.IntegralConfig;

public class IntegralConfigFrontDao extends BaseHibernateDao {

	public void saveIntegralConfig(IntegralConfig integralConfig) {
		save(integralConfig);
	}

	public void deleteIntegralConfig(IntegralConfig integralConfig) {
		delete(integralConfig);
	}

	public IntegralConfig getIntegralConfigById(int id) {
		return (IntegralConfig) findObjectById(IntegralConfig.class, id);
	}

	public void updateIntegralConfig(IntegralConfig integralConfig) {
		updateObject(integralConfig);
	}

	public IntegralConfig findIntegralConfigInList(int type) {
		IntegralConfig ic = null;
		String hql = "from IntegralConfig where type=" + type;
		List list = getListForHql(hql, null);
		if(list!=null && !list.isEmpty()){
			ic = (IntegralConfig) list.get(0);
		}
		return ic;
	}
}
