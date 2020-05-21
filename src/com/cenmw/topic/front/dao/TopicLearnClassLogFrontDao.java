package com.cenmw.topic.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.topic.po.TopicLearnClassLog;

public class TopicLearnClassLogFrontDao extends BaseHibernateDao {

	public void saveTopicLearnClassLog(TopicLearnClassLog topicLearnClassLog) {
		save(topicLearnClassLog);
	}

	public void deleteTopicLearnClassLog(TopicLearnClassLog topicLearnClassLog) {
		delete(topicLearnClassLog);
	}

	public TopicLearnClassLog getTopicLearnClassLogById(int id) {
		return (TopicLearnClassLog) findObjectById(TopicLearnClassLog.class, id);
	}

	public void updateTopicLearnClassLog(TopicLearnClassLog topicLearnClassLog) {
		updateObject(topicLearnClassLog);
	}

}
