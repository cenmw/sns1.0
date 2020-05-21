package com.cenmw.topic.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.topic.po.TopicLog;

public class TopicLogFrontDao extends BaseHibernateDao {

	public void saveTopicLog(TopicLog topicLog) {
		save(topicLog);
	}

	public void deleteTopicLog(TopicLog topicLog) {
		delete(topicLog);
	}

	public TopicLog getTopicLogById(int id) {
		return (TopicLog) findObjectById(TopicLog.class, id);
	}

	public void updateTopicLog(TopicLog topicLog) {
		updateObject(topicLog);
	}

}
