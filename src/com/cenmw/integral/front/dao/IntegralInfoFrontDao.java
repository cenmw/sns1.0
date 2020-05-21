package com.cenmw.integral.front.dao;

import java.util.List;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.integral.po.IntegralInfo;

public class IntegralInfoFrontDao extends BaseHibernateDao {

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

	public int getIntegralByMid(int mid) {
		String sql = "select sum(score) from integral_info where isdel=0 and mid="
				+ mid;
		List list = getListSql(sql);
		int sumscore = (Integer) list.get(0) == null ? 0 : (Integer) list
				.get(0);
		return sumscore;
	}

}
