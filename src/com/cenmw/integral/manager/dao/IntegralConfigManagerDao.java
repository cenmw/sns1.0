package com.cenmw.integral.manager.dao;

import java.util.List;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.integral.po.IntegralConfig;

public class IntegralConfigManagerDao extends BaseHibernateDao {

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

	public int getIntegralByMid(int mid) {
		String sql = "select sum(score) from integral_info where isdel=0 and mid="
				+ mid;
		List list = getListSql(sql);
		return new Integer((Integer) list.get(0)).intValue();
	}

}
