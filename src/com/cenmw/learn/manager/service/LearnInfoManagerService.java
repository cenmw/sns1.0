package com.cenmw.learn.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.learn.manager.dao.LearnInfoManagerDao;
import com.cenmw.learn.po.LearnInfo;
import com.cenmw.util.PageBean;

public class LearnInfoManagerService {
	private LearnInfoManagerDao learnInfoManagerDao;

	public PageBean findLearnInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return learnInfoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLearnInfo(LearnInfo learnInfo) {
		boolean status = true;
		learnInfoManagerDao.saveLearnInfo(learnInfo);
		learnInfoManagerDao.updateLearnInfo(learnInfo);
		return status;
	}

	public List findLearnInfoInList(int type) {
		String hql = "from LearnInfo where isdel=0 and type=" + type
				+ " order by id";
		return learnInfoManagerDao.getListForHql(hql, null);
	}

	public boolean updateLearnInfo(LearnInfo learnInfo) {
		boolean status = true;
		learnInfoManagerDao.updateLearnInfo(learnInfo);
		return status;
	}

	public LearnInfo getLearnInfo(int id) {
		return learnInfoManagerDao.getLearnInfoById(id);
	}

	public void deleteLearnInfoById(int id) {
		LearnInfo pb = getLearnInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			learnInfoManagerDao.saveLearnInfo(pb);
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

	public LearnInfoManagerDao getLearnInfoManagerDao() {
		return learnInfoManagerDao;
	}

	public void setLearnInfoManagerDao(LearnInfoManagerDao learnInfoManagerDao) {
		this.learnInfoManagerDao = learnInfoManagerDao;
	}


}
