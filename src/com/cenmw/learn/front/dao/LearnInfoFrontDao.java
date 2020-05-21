package com.cenmw.learn.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.learn.po.LearnInfo;

public class LearnInfoFrontDao extends BaseHibernateDao {

	public void saveLearnInfo(LearnInfo learnInfo) {
		save(learnInfo);
	}

	public void deleteLearnInfo(LearnInfo learnInfo) {
		delete(learnInfo);
	}

	public LearnInfo getLearnInfoById(int id) {
		return (LearnInfo) findObjectById(LearnInfo.class, id);
	}

	public void updateLearnInfo(LearnInfo learnInfo) {
		updateObject(learnInfo);
	}

}
