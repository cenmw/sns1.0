package com.cenmw.integral.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.integral.manager.dao.IntegralInfoManagerDao;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.util.PageBean;

public class IntegralInfoManagerService {
	private IntegralInfoManagerDao integralInfoManagerDao;

	public PageBean findIntegralInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return integralInfoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveIntegralInfo(IntegralInfo integralInfo) {
		boolean status = true;
		integralInfoManagerDao.saveIntegralInfo(integralInfo);
		integralInfoManagerDao.updateIntegralInfo(integralInfo);
		return status;
	}

	public List findIntegralInfoInList(int type) {
		String hql = "from IntegralInfo where isdel=0 and type=" + type
				+ " order by id";
		return integralInfoManagerDao.getListForHql(hql, null);
	}

	public int getIntegralByMid(int mid) {
		String sql = "select sum(score) from integral_info where isdel=0 and mid=" + mid;
		List list = integralInfoManagerDao.getListSql(sql);
		return new Integer((Integer)list.get(0)).intValue();
	}

	public boolean updateIntegralInfo(IntegralInfo integralInfo) {
		boolean status = true;
		integralInfoManagerDao.updateIntegralInfo(integralInfo);
		return status;
	}

	public IntegralInfo getIntegralInfo(int id) {
		return integralInfoManagerDao.getIntegralInfoById(id);
	}

	public void deleteIntegralInfoById(int id) {
		IntegralInfo pb = getIntegralInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			integralInfoManagerDao.saveIntegralInfo(pb);
		}
	}

	public void deleteIntegralInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteIntegralInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public IntegralInfoManagerDao getIntegralInfoManagerDao() {
		return integralInfoManagerDao;
	}

	public void setIntegralInfoManagerDao(
			IntegralInfoManagerDao integralInfoManagerDao) {
		this.integralInfoManagerDao = integralInfoManagerDao;
	}

}
