package com.cenmw.topic.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.topic.front.dao.TopicWhyLogFrontDao;
import com.cenmw.topic.po.TopicWhyLog;
import com.cenmw.util.PageBean;

public class TopicWhyLogFrontService {
	private TopicWhyLogFrontDao topicWhyLogFrontDao;

	public PageBean findTopicWhyLogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicWhyLogFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveTopicWhyLog(TopicWhyLog topicWhyLog) {
		boolean status = true;
		topicWhyLogFrontDao.saveTopicWhyLog(topicWhyLog);
		topicWhyLogFrontDao.updateTopicWhyLog(topicWhyLog);
		return status;
	}

	public List findTopicWhyLogInList(Integer tlid,int top) {
		String hql = "from TopicWhyLog where tlid="+tlid+" order by number desc , id asc";
		return topicWhyLogFrontDao.getListForHql(hql, null,top);
	}
	
	public TopicWhyLog getTopicWhyLog(Integer tlid, Integer whyid) {
		String hql = "from TopicWhyLog where tlid=" + tlid + " and whyid="
				+ whyid;
		List list = topicWhyLogFrontDao.getListForHql(hql, null);
		TopicWhyLog topicWhyLog = null;
		if (list != null && !list.isEmpty()) {
			topicWhyLog = (TopicWhyLog) list.get(0);
		}
		return topicWhyLog;
	}

	public boolean updateTopicWhyLog(TopicWhyLog topicWhyLog) {
		boolean status = true;
		topicWhyLogFrontDao.updateTopicWhyLog(topicWhyLog);
		return status;
	}

	public TopicWhyLog getTopicWhyLog(int id) {
		return topicWhyLogFrontDao.getTopicWhyLogById(id);
	}

	public TopicWhyLogFrontDao getTopicWhyLogFrontDao() {
		return topicWhyLogFrontDao;
	}

	public void setTopicWhyLogFrontDao(TopicWhyLogFrontDao topicWhyLogFrontDao) {
		this.topicWhyLogFrontDao = topicWhyLogFrontDao;
	}

}
