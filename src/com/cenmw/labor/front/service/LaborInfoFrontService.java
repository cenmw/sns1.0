package com.cenmw.labor.front.service;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.cenmw.labor.front.dao.LaborInfoFrontDao;
import com.cenmw.labor.po.LaborInfo;
import com.cenmw.util.DateUtil;
import com.cenmw.util.PageBean;

public class LaborInfoFrontService {
	private LaborInfoFrontDao laborInfoFrontDao;

	public PageBean findLaborInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return laborInfoFrontDao.findListHqlForPage(hql, orderstr, map, cpage,
				pageSize);
	}

	public boolean saveLaborInfo(LaborInfo laborInfo) {
		boolean status = true;
		laborInfoFrontDao.saveLaborInfo(laborInfo);
		laborInfoFrontDao.updateLaborInfo(laborInfo);
		return status;
	}

	public List findLaborInfoInList(int cid) {
		String hql = "from LaborInfo where isdel=0 and cid=" + cid
				+ " order by id";
		return laborInfoFrontDao.getListForHql(hql, null);
	}

	public int findLaborInfoListNum(int mid, int type, String lids) {
		String hql = "from LaborInfo where isdel=0 and endtime<'"
				+ DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")
				+ "' and mid=" + mid;
		if (type == 0) {
			if (lids == null || lids.length() == 0) {
				return 0;
			} else {
				hql = "from LaborInfo where isdel=0 and id in ("
						+ lids
						+ ") and endtime>='"
						+ DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")
						+ "' and starttime<='"
						+ DateUtil.getFormatDate(
								DateUtil.getCompareDateToDay(new Date(), 1),
								"yyyy-MM-dd") + "'";
			}
		}
		return laborInfoFrontDao.findAllRow(hql);
	}

	public boolean updateLaborInfo(LaborInfo laborInfo) {
		boolean status = true;
		laborInfoFrontDao.updateLaborInfo(laborInfo);
		return status;
	}

	public LaborInfo getLaborInfo(int id) {
		return laborInfoFrontDao.getLaborInfoById(id);
	}

	public void deleteLaborInfoById(int id) {
		LaborInfo pb = getLaborInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			laborInfoFrontDao.saveLaborInfo(pb);
		}
	}

	public void deleteLaborInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteLaborInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public LaborInfoFrontDao getLaborInfoFrontDao() {
		return laborInfoFrontDao;
	}

	public void setLaborInfoFrontDao(LaborInfoFrontDao laborInfoFrontDao) {
		this.laborInfoFrontDao = laborInfoFrontDao;
	}

	public List findLaborInfoList(int top) {
		if (top > 100) {
			top = 100;
		}
		String hql = "from LaborInfo where isdel=0 order by id";
		return laborInfoFrontDao.getListForHql(hql, null, top);
	}

}
