package com.cenmw.learn.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.learn.po.LearnWhy;

public class LearnWhyFrontDao extends BaseHibernateDao {

	public void saveLearnWhy(LearnWhy learnWhy) {
		save(learnWhy);
	}

	public void deleteLearnWhy(LearnWhy learnWhy) {
		delete(learnWhy);
	}

	public LearnWhy getLearnWhyById(int id) {
		return (LearnWhy) findObjectById(LearnWhy.class, id);
	}

	public void updateLearnWhy(LearnWhy learnWhy) {
		updateObject(learnWhy);
	}

}
