package com.cenmw.labor.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.labor.front.dao.LaborReplyInfoFrontDao;
import com.cenmw.labor.po.LaborReplyInfo;
import com.cenmw.util.PageBean;

public class LaborReplyInfoFrontService {
	private LaborReplyInfoFrontDao laborReplyInfoFrontDao;

	public PageBean findLaborReplyInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return laborReplyInfoFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLaborReplyInfo(LaborReplyInfo laborReplyInfo) {
		boolean status = true;
		laborReplyInfoFrontDao.saveLaborReplyInfo(laborReplyInfo);
		laborReplyInfoFrontDao.updateLaborReplyInfo(laborReplyInfo);
		return status;
	}

	public List findLaborReplyInfoInList(int cid) {
		String hql = "from LaborReplyInfo where isdel=0 and cid=" + cid
				+ " order by sort asc";
		return laborReplyInfoFrontDao.getListForHql(hql, null);
	}

	public int findLaborReplyInfoCount(int cid) {
		String hql = "from LaborReplyInfo where isdel=0 and cid=" + cid;
		return laborReplyInfoFrontDao.findAllRow(hql);
	}
	
	public String findLaborReplyInfoIds(int cid) {
		String hql = "from LaborReplyInfo where isdel=0 and cid=" + cid;
		List list = laborReplyInfoFrontDao.getListForHql(hql, null);
		String ids = "";
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				LaborReplyInfo lr = (LaborReplyInfo) list.get(i);
				int id = lr.getMid().intValue();
				if (ids.length() == 0) {
					ids += id;
				} else {
					ids += "," + ids;
				}
			}
		}
		return ids;
	}
	
	public int findLaborReplyInfoCount(int cid, int mid) {
		String hql = "from LaborReplyInfo where isdel=0 and cid=" + cid
				+ " and mid=" + mid;
		return laborReplyInfoFrontDao.findAllRow(hql);
	}

	public int findLaborReplyInfoIsgreeCount(int cid) {
		String hql = "from LaborReplyInfo where isdel=0 and isagree=1 and cid="
				+ cid;
		return laborReplyInfoFrontDao.findAllRow(hql);
	}

	public String findLaborReplyInfos(int mid) {
		String hql = "from LaborReplyInfo where isdel=0 and mid=" + mid
				+ " order by id";
		List list = laborReplyInfoFrontDao.getListForHql(hql, null);
		String fids = "";
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				LaborReplyInfo lr = (LaborReplyInfo) list.get(i);
				int fid = lr.getCid().intValue();
				if (fids.length() == 0) {
					fids += fid;
				} else {
					fids += "," + fid;
				}
			}
		}
		return fids;
	}

	public boolean updateLaborReplyInfo(LaborReplyInfo laborReplyInfo) {
		boolean status = true;
		laborReplyInfoFrontDao.updateLaborReplyInfo(laborReplyInfo);
		return status;
	}

	public LaborReplyInfo getLaborReplyInfo(int id) {
		return laborReplyInfoFrontDao.getLaborReplyInfoById(id);
	}

	public void deleteLaborReplyInfoById(int id) {
		LaborReplyInfo pb = getLaborReplyInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			laborReplyInfoFrontDao.saveLaborReplyInfo(pb);
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

	public LaborReplyInfoFrontDao getLaborReplyInfoFrontDao() {
		return laborReplyInfoFrontDao;
	}

	public void setLaborReplyInfoFrontDao(
			LaborReplyInfoFrontDao laborReplyInfoFrontDao) {
		this.laborReplyInfoFrontDao = laborReplyInfoFrontDao;
	}

}
