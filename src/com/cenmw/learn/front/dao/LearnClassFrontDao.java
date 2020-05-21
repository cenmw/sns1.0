package com.cenmw.learn.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.learn.po.LearnClass;

public class LearnClassFrontDao extends BaseHibernateDao {

	public void saveLearnClass(LearnClass learnClass) {
		save(learnClass);
	}

	public void deleteLearnClass(LearnClass learnClass) {
		delete(learnClass);
	}

	public LearnClass getLearnClassById(int id) {
		return (LearnClass) findObjectById(LearnClass.class, id);
	}

	public void updateLearnClass(LearnClass learnClass) {
		updateObject(learnClass);
	}

}
