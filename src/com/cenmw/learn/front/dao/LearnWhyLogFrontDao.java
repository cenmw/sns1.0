package com.cenmw.learn.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.learn.po.LearnWhyLog;

public class LearnWhyLogFrontDao extends BaseHibernateDao {

	public void saveLearnWhyLog(LearnWhyLog learnWhyLog) {
		save(learnWhyLog);
	}

	public void deleteLearnWhyLog(LearnWhyLog learnWhyLog) {
		delete(learnWhyLog);
	}

	public LearnWhyLog getLearnWhyLogById(int id) {
		return (LearnWhyLog) findObjectById(LearnWhyLog.class, id);
	}

	public void updateLearnWhyLog(LearnWhyLog learnWhyLog) {
		updateObject(learnWhyLog);
	}

}
