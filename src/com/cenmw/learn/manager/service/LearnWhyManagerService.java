package com.cenmw.learn.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.learn.manager.dao.LearnWhyManagerDao;
import com.cenmw.learn.po.LearnWhy;
import com.cenmw.util.PageBean;

public class LearnWhyManagerService {
	private LearnWhyManagerDao learnWhyManagerDao;

	public PageBean findLearnWhyHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return learnWhyManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLearnWhy(LearnWhy learnWhy) {
		boolean status = true;
		learnWhyManagerDao.saveLearnWhy(learnWhy);
		learnWhyManagerDao.updateLearnWhy(learnWhy);
		return status;
	}

	public List findLearnWhyInList() {
		String hql = "from LearnWhy where isdel=0 order by sort desc , id asc";
		return learnWhyManagerDao.getListForHql(hql, null);
	}
	
	public boolean updateLearnWhy(LearnWhy learnWhy) {
		boolean status = true;
		learnWhyManagerDao.updateLearnWhy(learnWhy);
		return status;
	}

	public LearnWhy getLearnWhy(int id) {
		return learnWhyManagerDao.getLearnWhyById(id);
	}

	public void deleteLearnWhyById(int id) {
		LearnWhy pb = getLearnWhy(id);
		if (pb != null) {
			pb.setIsdel(1);
			learnWhyManagerDao.saveLearnWhy(pb);
		}
	}

	public void deleteLearnWhyByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteLearnWhyById(new Integer(idsArr[i]));
			}
		}
	}

	public LearnWhyManagerDao getLearnWhyManagerDao() {
		return learnWhyManagerDao;
	}

	public void setLearnWhyManagerDao(LearnWhyManagerDao learnWhyManagerDao) {
		this.learnWhyManagerDao = learnWhyManagerDao;
	}


}
