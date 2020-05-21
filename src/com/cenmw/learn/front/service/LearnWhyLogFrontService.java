package com.cenmw.learn.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.learn.front.dao.LearnWhyLogFrontDao;
import com.cenmw.learn.po.LearnWhyLog;
import com.cenmw.util.PageBean;

public class LearnWhyLogFrontService {
	private LearnWhyLogFrontDao learnWhyLogFrontDao;

	public PageBean findLearnWhyLogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return learnWhyLogFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLearnWhyLog(LearnWhyLog learnWhyLog) {
		boolean status = true;
		learnWhyLogFrontDao.saveLearnWhyLog(learnWhyLog);
		learnWhyLogFrontDao.updateLearnWhyLog(learnWhyLog);
		return status;
	}

	public List findLearnWhyLogInList(Integer llid,int top) {
		String hql = "from LearnWhyLog where llid=" + llid + " order by number desc , id asc";
		return learnWhyLogFrontDao.getListForHql(hql, null,top);
	}
	
	public LearnWhyLog getLearnWhyLog(Integer llid, Integer whyid) {
		String hql = "from LearnWhyLog where llid=" + llid + " and whyid="
				+ whyid;
		List list = learnWhyLogFrontDao.getListForHql(hql, null);
		LearnWhyLog learnWhyLog = null;
		if (list != null && !list.isEmpty()) {
			learnWhyLog = (LearnWhyLog) list.get(0);
		}
		return learnWhyLog;
	}

	public boolean updateLearnWhyLog(LearnWhyLog learnWhyLog) {
		boolean status = true;
		learnWhyLogFrontDao.updateLearnWhyLog(learnWhyLog);
		return status;
	}

	public LearnWhyLog getLearnWhyLog(int id) {
		return learnWhyLogFrontDao.getLearnWhyLogById(id);
	}

	public LearnWhyLogFrontDao getLearnWhyLogFrontDao() {
		return learnWhyLogFrontDao;
	}

	public void setLearnWhyLogFrontDao(LearnWhyLogFrontDao learnWhyLogFrontDao) {
		this.learnWhyLogFrontDao = learnWhyLogFrontDao;
	}

}
