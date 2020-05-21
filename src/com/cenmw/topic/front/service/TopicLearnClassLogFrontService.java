package com.cenmw.topic.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.topic.front.dao.TopicLearnClassLogFrontDao;
import com.cenmw.topic.po.TopicLearnClassLog;
import com.cenmw.topic.po.TopicLearnClassLog;
import com.cenmw.util.PageBean;

public class TopicLearnClassLogFrontService {
	private TopicLearnClassLogFrontDao topicLearnClassLogFrontDao;

	public PageBean findTopicLearnClassLogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicLearnClassLogFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveTopicLearnClassLog(TopicLearnClassLog topicLearnClassLog) {
		boolean status = true;
		topicLearnClassLogFrontDao.saveTopicLearnClassLog(topicLearnClassLog);
		topicLearnClassLogFrontDao.updateTopicLearnClassLog(topicLearnClassLog);
		return status;
	}

	public List findTopicLearnClassLogInList(Integer tlid,int top) {
		String hql = "from TopicLearnClassLog where tlid=" + tlid + " order by number desc , id asc";
		return topicLearnClassLogFrontDao.getListForHql(hql, null,top);
	}
	
	public TopicLearnClassLog getTopicLearnClassLog(Integer tlid, Integer lcid) {
		String hql = "from TopicLearnClassLog where tlid=" + tlid + " and lcid="
				+ lcid;
		List list = topicLearnClassLogFrontDao.getListForHql(hql, null);
		TopicLearnClassLog topicLearnClassLog = null;
		if (list != null && !list.isEmpty()) {
			topicLearnClassLog = (TopicLearnClassLog) list.get(0);
		}
		return topicLearnClassLog;
	}
	
	public boolean updateTopicLearnClassLog(TopicLearnClassLog topicLearnClassLog) {
		boolean status = true;
		topicLearnClassLogFrontDao.updateTopicLearnClassLog(topicLearnClassLog);
		return status;
	}

	public TopicLearnClassLog getTopicLearnClassLog(int id) {
		return topicLearnClassLogFrontDao.getTopicLearnClassLogById(id);
	}

	public TopicLearnClassLogFrontDao getTopicLearnClassLogFrontDao() {
		return topicLearnClassLogFrontDao;
	}

	public void setTopicLearnClassLogFrontDao(TopicLearnClassLogFrontDao topicLearnClassLogFrontDao) {
		this.topicLearnClassLogFrontDao = topicLearnClassLogFrontDao;
	}

}
