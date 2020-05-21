package com.cenmw.integral.front.service;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.cenmw.integral.front.dao.IntegralInfoFrontDao;
import com.cenmw.integral.po.IntegralInfo;
import com.cenmw.util.DateUtil;
import com.cenmw.util.PageBean;

public class IntegralInfoFrontService {
	private IntegralInfoFrontDao integralInfoFrontDao;

	public PageBean findIntegralInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return integralInfoFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveIntegralInfo(IntegralInfo integralInfo) {
		boolean status = true;
		integralInfoFrontDao.saveIntegralInfo(integralInfo);
		integralInfoFrontDao.updateIntegralInfo(integralInfo);
		return status;
	}

	public List findIntegralInfoInList(int type) {
		String hql = "from IntegralInfo where isdel=0 and type=" + type
				+ " order by id";
		return integralInfoFrontDao.getListForHql(hql, null);
	}

	public IntegralInfo deleteIntegralInfoByCid(int type, int cid, int mid) {
		IntegralInfo ii = null;
		String hql = "from IntegralInfo where isdel=0 and type=" + type
				+ " and cid=" + cid + " and mid=" + mid + " order by id";
		List list = integralInfoFrontDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			ii = (IntegralInfo) list.get(0);
			ii.setIsdel(1);
			integralInfoFrontDao.updateIntegralInfo(ii);
		}
		return ii;
	}
	
	public IntegralInfo deleteIntegralInfoByCid(int type, int cid) {
		IntegralInfo ii = null;
		String hql = "from IntegralInfo where isdel=0 and type=" + type
				+ " and cid=" + cid + " order by id";
		List list = integralInfoFrontDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			ii = (IntegralInfo) list.get(0);
			ii.setIsdel(1);
			integralInfoFrontDao.updateIntegralInfo(ii);
		}
		return ii;
	}

	public int getIntegralByMid(int mid) {
		String sql = "select sum(score) from integral_info where isdel=0 and mid="
				+ mid;
		List list = integralInfoFrontDao.getListSql(sql);
		return new Integer((Integer) list.get(0)).intValue();
	}

	public int getIntegralByDate(int mid) {
		String date = DateUtil.getFormatDate(new Date(), "yyyy-MM-dd");
		String sql = "select count(*) from integral_info where isdel=0 and mid="
				+ mid
				+ " and ctime>='"
				+ date
				+ " 00:00:00' and ctime<='"
				+ date + " 23:59:59'";
		List list = integralInfoFrontDao.getListSql(sql);
		return new Integer((Integer) list.get(0)).intValue();
	}

	public boolean updateIntegralInfo(IntegralInfo integralInfo) {
		boolean status = true;
		integralInfoFrontDao.updateIntegralInfo(integralInfo);
		return status;
	}

	public IntegralInfo getIntegralInfo(int id) {
		return integralInfoFrontDao.getIntegralInfoById(id);
	}

	public void deleteIntegralInfoById(int id) {
		IntegralInfo pb = getIntegralInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			integralInfoFrontDao.saveIntegralInfo(pb);
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

	public IntegralInfoFrontDao getIntegralInfoFrontDao() {
		return integralInfoFrontDao;
	}

	public void setIntegralInfoFrontDao(
			IntegralInfoFrontDao integralInfoFrontDao) {
		this.integralInfoFrontDao = integralInfoFrontDao;
	}

}
