package com.cenmw.topic.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.topic.po.TopicWhyLog;

public class TopicWhyLogFrontDao extends BaseHibernateDao {

	public void saveTopicWhyLog(TopicWhyLog topicWhyLog) {
		save(topicWhyLog);
	}

	public void deleteTopicWhyLog(TopicWhyLog topicWhyLog) {
		delete(topicWhyLog);
	}

	public TopicWhyLog getTopicWhyLogById(int id) {
		return (TopicWhyLog) findObjectById(TopicWhyLog.class, id);
	}

	public void updateTopicWhyLog(TopicWhyLog topicWhyLog) {
		updateObject(topicWhyLog);
	}

}
