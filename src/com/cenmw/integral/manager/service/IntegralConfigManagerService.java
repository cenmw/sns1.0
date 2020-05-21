package com.cenmw.integral.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.integral.manager.dao.IntegralConfigManagerDao;
import com.cenmw.integral.po.IntegralConfig;
import com.cenmw.util.PageBean;

public class IntegralConfigManagerService {
	private IntegralConfigManagerDao integralConfigManagerDao;

	public PageBean findIntegralConfigHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return integralConfigManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveIntegralConfig(IntegralConfig integralConfig) {
		boolean status = true;
		integralConfigManagerDao.saveIntegralConfig(integralConfig);
		integralConfigManagerDao.updateIntegralConfig(integralConfig);
		return status;
	}

	public IntegralConfig findIntegralConfigInList(int type) {
		IntegralConfig ic = null;
		String hql = "from IntegralConfig where type=" + type;
		List list = integralConfigManagerDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			ic = (IntegralConfig) list.get(0);
		}
		return ic;
	}

	public int getIntegralByMid(int mid) {
		return integralConfigManagerDao.getIntegralByMid(mid);
	}

	public boolean updateIntegralConfig(IntegralConfig integralConfig) {
		boolean status = true;
		integralConfigManagerDao.updateIntegralConfig(integralConfig);
		return status;
	}

	public IntegralConfig getIntegralConfig(int id) {
		return integralConfigManagerDao.getIntegralConfigById(id);
	}

	public IntegralConfigManagerDao getIntegralConfigManagerDao() {
		return integralConfigManagerDao;
	}

	public void setIntegralConfigManagerDao(
			IntegralConfigManagerDao integralConfigManagerDao) {
		this.integralConfigManagerDao = integralConfigManagerDao;
	}

}
