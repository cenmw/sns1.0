package com.cenmw.topic.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.topic.po.TopicLifeAdviceLog;

public class TopicLifeAdviceLogFrontDao extends BaseHibernateDao {

	public void saveTopicLifeAdviceLog(TopicLifeAdviceLog topicLifeAdviceLog) {
		save(topicLifeAdviceLog);
	}

	public void deleteTopicLifeAdviceLog(TopicLifeAdviceLog topicLifeAdviceLog) {
		delete(topicLifeAdviceLog);
	}

	public TopicLifeAdviceLog getTopicLifeAdviceLogById(int id) {
		return (TopicLifeAdviceLog) findObjectById(TopicLifeAdviceLog.class, id);
	}

	public void updateTopicLifeAdviceLog(TopicLifeAdviceLog topicLifeAdviceLog) {
		updateObject(topicLifeAdviceLog);
	}

}
