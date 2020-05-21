package com.cenmw.learn.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.learn.front.dao.LearnInfoFrontDao;
import com.cenmw.learn.po.LearnInfo;
import com.cenmw.util.PageBean;

public class LearnInfoFrontService {
	private LearnInfoFrontDao learnInfoFrontDao;

	public PageBean findLearnInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return learnInfoFrontDao.findListHqlForPage(hql, orderstr, map, cpage,
				pageSize);
	}

	public boolean saveLearnInfo(LearnInfo learnInfo) {
		boolean status = true;
		learnInfoFrontDao.saveLearnInfo(learnInfo);
		learnInfoFrontDao.updateLearnInfo(learnInfo);
		return status;
	}

	public List findLearnInfoInList(int type) {
		String hql = "from LearnInfo where isdel=0 and type=" + type
				+ " order by id";
		return learnInfoFrontDao.getListForHql(hql, null);
	}

	public int getMemberLearnInListNumber(int cid) {
		String hql = "from LearnInfo where isdel=0 and rcid=" + cid;
		return learnInfoFrontDao.findAllRow(hql);
	}

	public boolean updateLearnInfo(LearnInfo learnInfo) {
		boolean status = true;
		learnInfoFrontDao.updateLearnInfo(learnInfo);
		return status;
	}

	public LearnInfo getLearnInfo(int id) {
		return learnInfoFrontDao.getLearnInfoById(id);
	}

	public LearnInfo findNextLearnInfo(int code) {
		String hql = "from LearnInfo where isdel=0 and code=" + code;
		List list = learnInfoFrontDao.getListForHql(hql, null);
		LearnInfo li = null;
		if (list != null && !list.isEmpty()) {
			li = (LearnInfo) list.get(0);
		}
		return li;
	}

	public void deleteLearnInfoById(int id) {
		LearnInfo pb = getLearnInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			learnInfoFrontDao.saveLearnInfo(pb);
		}
	}

	public void deleteLearnInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteLearnInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public LearnInfoFrontDao getLearnInfoFrontDao() {
		return learnInfoFrontDao;
	}

	public void setLearnInfoFrontDao(LearnInfoFrontDao learnInfoFrontDao) {
		this.learnInfoFrontDao = learnInfoFrontDao;
	}

}
