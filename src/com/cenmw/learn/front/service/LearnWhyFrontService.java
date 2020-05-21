package com.cenmw.learn.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.learn.front.dao.LearnWhyFrontDao;
import com.cenmw.learn.po.LearnWhy;
import com.cenmw.util.PageBean;

public class LearnWhyFrontService {
	private LearnWhyFrontDao learnWhyFrontDao;

	public PageBean findLearnWhyHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return learnWhyFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLearnWhy(LearnWhy learnWhy) {
		boolean status = true;
		learnWhyFrontDao.saveLearnWhy(learnWhy);
		learnWhyFrontDao.updateLearnWhy(learnWhy);
		return status;
	}

	public List findLearnWhyInList() {
		String hql = "from LearnWhy where isdel=0 order by sort desc , id asc";
		return learnWhyFrontDao.getListForHql(hql, null);
	}
	
	public boolean updateLearnWhy(LearnWhy learnWhy) {
		boolean status = true;
		learnWhyFrontDao.updateLearnWhy(learnWhy);
		return status;
	}

	public LearnWhy getLearnWhy(int id) {
		return learnWhyFrontDao.getLearnWhyById(id);
	}

	public void deleteLearnWhyById(int id) {
		LearnWhy pb = getLearnWhy(id);
		if (pb != null) {
			pb.setIsdel(1);
			learnWhyFrontDao.saveLearnWhy(pb);
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

	public LearnWhyFrontDao getLearnWhyFrontDao() {
		return learnWhyFrontDao;
	}

	public void setLearnWhyFrontDao(LearnWhyFrontDao learnWhyFrontDao) {
		this.learnWhyFrontDao = learnWhyFrontDao;
	}


}
