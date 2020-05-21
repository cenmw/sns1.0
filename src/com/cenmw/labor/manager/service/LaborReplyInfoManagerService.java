package com.cenmw.labor.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.labor.manager.dao.LaborReplyInfoManagerDao;
import com.cenmw.labor.po.LaborReplyInfo;
import com.cenmw.util.PageBean;

public class LaborReplyInfoManagerService {
	private LaborReplyInfoManagerDao laborReplyInfoManagerDao;

	public PageBean findLaborReplyInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return laborReplyInfoManagerDao.findListHqlForPage(hql, orderstr,
				map, cpage, pageSize);
	}

	public boolean saveLaborReplyInfo(LaborReplyInfo laborReplyInfo) {
		boolean status = true;
		laborReplyInfoManagerDao.saveLaborReplyInfo(laborReplyInfo);
		laborReplyInfoManagerDao.updateLaborReplyInfo(laborReplyInfo);
		return status;
	}

	public List findLaborReplyInfoInList(int cid) {
		String hql = "from LaborReplyInfo where isdel=0 and cid=" + cid
				+ " order by isagree desc,sort asc";
		return laborReplyInfoManagerDao.getListForHql(hql, null);
	}

	public int findLaborReplyInfoCount(int cid) {
		String hql = "from LaborReplyInfo where isdel=0 and cid=" + cid;
		return laborReplyInfoManagerDao.findAllRow(hql);
	}
	
	public int findLaborReplyInfoIsgreeCount(int cid) {
		String hql = "from LaborReplyInfo where isdel=0 and isagree=1 and cid=" + cid;
		return laborReplyInfoManagerDao.findAllRow(hql);
	}

	public boolean updateLaborReplyInfo(LaborReplyInfo laborReplyInfo) {
		boolean status = true;
		laborReplyInfoManagerDao.updateLaborReplyInfo(laborReplyInfo);
		return status;
	}

	public LaborReplyInfo getLaborReplyInfo(int id) {
		return laborReplyInfoManagerDao.getLaborReplyInfoById(id);
	}

	public void deleteLaborReplyInfoById(int id) {
		LaborReplyInfo pb = getLaborReplyInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			laborReplyInfoManagerDao.saveLaborReplyInfo(pb);
		}
	}

	public void deleteLaborReplyInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteLaborReplyInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public LaborReplyInfoManagerDao getLaborReplyInfoManagerDao() {
		return laborReplyInfoManagerDao;
	}

	public void setLaborReplyInfoManagerDao(
			LaborReplyInfoManagerDao laborReplyInfoManagerDao) {
		this.laborReplyInfoManagerDao = laborReplyInfoManagerDao;
	}

}
