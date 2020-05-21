package com.cenmw.learn.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.learn.front.dao.LearnClassFrontDao;
import com.cenmw.learn.po.LearnClass;
import com.cenmw.util.PageBean;

public class LearnClassFrontService {
	private LearnClassFrontDao learnClassFrontDao;

	public PageBean findLearnClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return learnClassFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLearnClass(LearnClass learnClass) {
		boolean status = true;
		learnClassFrontDao.saveLearnClass(learnClass);
		learnClassFrontDao.updateLearnClass(learnClass);
		return status;
	}

	public List findLearnClassInList() {
		String hql = "from LearnClass where isdel=0 order by sort desc, id desc";
		return learnClassFrontDao.getListForHql(hql, null);
	}
	
	public boolean updateLearnClass(LearnClass learnClass) {
		boolean status = true;
		learnClassFrontDao.updateLearnClass(learnClass);
		return status;
	}

	public LearnClass getLearnClass(int id) {
		return learnClassFrontDao.getLearnClassById(id);
	}

	public void deleteLearnClassById(int id) {
		LearnClass pb = getLearnClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			learnClassFrontDao.saveLearnClass(pb);
		}
	}

	public void deleteLearnClassByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteLearnClassById(new Integer(idsArr[i]));
			}
		}
	}

	public LearnClassFrontDao getLearnClassFrontDao() {
		return learnClassFrontDao;
	}

	public void setLearnClassFrontDao(LearnClassFrontDao learnClassFrontDao) {
		this.learnClassFrontDao = learnClassFrontDao;
	}


}
