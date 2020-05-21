package com.cenmw.topic.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.topic.po.TopicLifeAdvice;

public class TopicLifeAdviceFrontDao extends BaseHibernateDao {

	public void saveTopicLifeAdvice(TopicLifeAdvice topicLifeAdvice) {
		save(topicLifeAdvice);
	}

	public void deleteTopicLifeAdvice(TopicLifeAdvice topicLifeAdvice) {
		delete(topicLifeAdvice);
	}

	public TopicLifeAdvice getTopicLifeAdviceById(int id) {
		return (TopicLifeAdvice) findObjectById(TopicLifeAdvice.class, id);
	}

	public void updateTopicLifeAdvice(TopicLifeAdvice topicLifeAdvice) {
		updateObject(topicLifeAdvice);
	}

}
