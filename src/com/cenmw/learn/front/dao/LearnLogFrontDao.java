package com.cenmw.learn.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.learn.po.LearnLog;

public class LearnLogFrontDao extends BaseHibernateDao {

	public void saveLearnLog(LearnLog learnLog) {
		save(learnLog);
	}

	public void deleteLearnLog(LearnLog learnLog) {
		delete(learnLog);
	}

	public LearnLog getLearnLogById(int id) {
		return (LearnLog) findObjectById(LearnLog.class, id);
	}

	public void updateLearnLog(LearnLog learnLog) {
		updateObject(learnLog);
	}

}
