package com.cenmw.topic.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.topic.po.TopicClass;

public class TopicClassManagerDao extends BaseHibernateDao {

	public void saveTopicClass(TopicClass topicClass) {
		save(topicClass);
	}

	public void deleteTopicClass(TopicClass topicClass) {
		delete(topicClass);
	}

	public TopicClass getTopicClassById(int id) {
		return (TopicClass) findObjectById(TopicClass.class, id);
	}

	public void updateTopicClass(TopicClass topicClass) {
		updateObject(topicClass);
	}

}
