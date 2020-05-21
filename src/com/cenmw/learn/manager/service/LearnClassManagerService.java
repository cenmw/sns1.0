package com.cenmw.learn.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.learn.manager.dao.LearnClassManagerDao;
import com.cenmw.learn.po.LearnClass;
import com.cenmw.util.PageBean;

public class LearnClassManagerService {
	private LearnClassManagerDao learnClassManagerDao;

	public PageBean findLearnClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return learnClassManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLearnClass(LearnClass learnClass) {
		boolean status = true;
		learnClassManagerDao.saveLearnClass(learnClass);
		learnClassManagerDao.updateLearnClass(learnClass);
		return status;
	}

	public List findLearnClassInList() {
		String hql = "from LearnClass where isdel=0 order by sort desc , id desc";
		return learnClassManagerDao.getListForHql(hql, null);
	}
	
	public boolean updateLearnClass(LearnClass learnClass) {
		boolean status = true;
		learnClassManagerDao.updateLearnClass(learnClass);
		return status;
	}

	public LearnClass getLearnClass(int id) {
		return learnClassManagerDao.getLearnClassById(id);
	}

	public void deleteLearnClassById(int id) {
		LearnClass pb = getLearnClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			learnClassManagerDao.saveLearnClass(pb);
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

	public LearnClassManagerDao getLearnClassManagerDao() {
		return learnClassManagerDao;
	}

	public void setLearnClassManagerDao(LearnClassManagerDao learnClassManagerDao) {
		this.learnClassManagerDao = learnClassManagerDao;
	}


}
