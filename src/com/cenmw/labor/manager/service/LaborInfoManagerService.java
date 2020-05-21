package com.cenmw.labor.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.labor.manager.dao.LaborInfoManagerDao;
import com.cenmw.labor.po.LaborInfo;
import com.cenmw.util.PageBean;

public class LaborInfoManagerService {
	private LaborInfoManagerDao laborInfoManagerDao;

	public PageBean findLaborInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return laborInfoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLaborInfo(LaborInfo laborInfo) {
		boolean status = true;
		laborInfoManagerDao.saveLaborInfo(laborInfo);
		laborInfoManagerDao.updateLaborInfo(laborInfo);
		return status;
	}

	public List findLaborInfoInList(int cid) {
		String hql = "from LaborInfo where isdel=0 and cid=" + cid
				+ " order by id";
		return laborInfoManagerDao.getListForHql(hql, null);
	}

	public boolean updateLaborInfo(LaborInfo laborInfo) {
		boolean status = true;
		laborInfoManagerDao.updateLaborInfo(laborInfo);
		return status;
	}

	public LaborInfo getLaborInfo(int id) {
		return laborInfoManagerDao.getLaborInfoById(id);
	}

	public void deleteLaborInfoById(int id) {
		LaborInfo pb = getLaborInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			laborInfoManagerDao.saveLaborInfo(pb);
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

	public LaborInfoManagerDao getLaborInfoManagerDao() {
		return laborInfoManagerDao;
	}

	public void setLaborInfoManagerDao(LaborInfoManagerDao laborInfoManagerDao) {
		this.laborInfoManagerDao = laborInfoManagerDao;
	}


}
