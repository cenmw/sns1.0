package com.cenmw.topic.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.topic.front.dao.TopicLifeAdviceLogFrontDao;
import com.cenmw.topic.po.TopicLifeAdviceLog;
import com.cenmw.util.PageBean;

public class TopicLifeAdviceLogFrontService {
	private TopicLifeAdviceLogFrontDao topicLifeAdviceLogFrontDao;

	public PageBean findTopicLifeAdviceLogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicLifeAdviceLogFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveTopicLifeAdviceLog(TopicLifeAdviceLog topicLifeAdviceLog) {
		boolean status = true;
		topicLifeAdviceLogFrontDao.saveTopicLifeAdviceLog(topicLifeAdviceLog);
		topicLifeAdviceLogFrontDao.updateTopicLifeAdviceLog(topicLifeAdviceLog);
		return status;
	}

	public List findTopicLifeAdviceLogInList(Integer tlid,int top) {
		String hql = "from TopicLifeAdviceLog where tlid=" + tlid + " order by number desc , id asc";
		return topicLifeAdviceLogFrontDao.getListForHql(hql, null,top);
	}
	
	public TopicLifeAdviceLog getTopicLifeAdviceLog(Integer tlid, Integer ttaid) {
		String hql = "from TopicLifeAdviceLog where tlid=" + tlid + " and ttaid="
				+ ttaid;
		List list = topicLifeAdviceLogFrontDao.getListForHql(hql, null);
		TopicLifeAdviceLog topicLifeAdviceLog = null;
		if (list != null && !list.isEmpty()) {
			topicLifeAdviceLog = (TopicLifeAdviceLog) list.get(0);
		}
		return topicLifeAdviceLog;
	}
	
	public boolean updateTopicLifeAdviceLog(TopicLifeAdviceLog topicLifeAdviceLog) {
		boolean status = true;
		topicLifeAdviceLogFrontDao.updateTopicLifeAdviceLog(topicLifeAdviceLog);
		return status;
	}

	public TopicLifeAdviceLog getTopicLifeAdviceLog(int id) {
		return topicLifeAdviceLogFrontDao.getTopicLifeAdviceLogById(id);
	}

	public TopicLifeAdviceLogFrontDao getTopicLifeAdviceLogFrontDao() {
		return topicLifeAdviceLogFrontDao;
	}

	public void setTopicLifeAdviceLogFrontDao(TopicLifeAdviceLogFrontDao topicLifeAdviceLogFrontDao) {
		this.topicLifeAdviceLogFrontDao = topicLifeAdviceLogFrontDao;
	}

}
