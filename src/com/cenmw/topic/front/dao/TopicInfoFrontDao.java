package com.cenmw.topic.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.topic.po.TopicInfo;

public class TopicInfoFrontDao extends BaseHibernateDao {

	public void saveTopicInfo(TopicInfo topicInfo) {
		save(topicInfo);
	}

	public void deleteTopicInfo(TopicInfo topicInfo) {
		delete(topicInfo);
	}

	public TopicInfo getTopicInfoById(int id) {
		return (TopicInfo) findObjectById(TopicInfo.class, id);
	}

	public void updateTopicInfo(TopicInfo topicInfo) {
		updateObject(topicInfo);
	}

}
