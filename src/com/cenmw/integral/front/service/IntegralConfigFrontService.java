package com.cenmw.integral.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.integral.front.dao.IntegralConfigFrontDao;
import com.cenmw.integral.po.IntegralConfig;
import com.cenmw.util.PageBean;

public class IntegralConfigFrontService {
	private IntegralConfigFrontDao integralConfigFrontDao;

	public PageBean findIntegralConfigHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return integralConfigFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveIntegralConfig(IntegralConfig integralConfig) {
		boolean status = true;
		integralConfigFrontDao.saveIntegralConfig(integralConfig);
		integralConfigFrontDao.updateIntegralConfig(integralConfig);
		return status;
	}

	public IntegralConfig findIntegralConfigInList(int type) {
		IntegralConfig ic = null;
		String hql = "from IntegralConfig where type=" + type;
		List list = integralConfigFrontDao.getListForHql(hql, null);
		if(list!=null && !list.isEmpty()){
			ic = (IntegralConfig) list.get(0);
		}
		return ic;
	}

	public int getIntegralByMid(int mid) {
		String sql = "select sum(score) from integral_info where isdel=0 and mid=" + mid;
		List list = integralConfigFrontDao.getListSql(sql);
		return new Integer((Integer)list.get(0)).intValue();
	}

	public boolean updateIntegralConfig(IntegralConfig integralConfig) {
		boolean status = true;
		integralConfigFrontDao.updateIntegralConfig(integralConfig);
		return status;
	}

	public IntegralConfig getIntegralConfig(int id) {
		return integralConfigFrontDao.getIntegralConfigById(id);
	}

	public IntegralConfigFrontDao getIntegralConfigFrontDao() {
		return integralConfigFrontDao;
	}

	public void setIntegralConfigFrontDao(
			IntegralConfigFrontDao integralConfigFrontDao) {
		this.integralConfigFrontDao = integralConfigFrontDao;
	}

}
