package com.cenmw.topic.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.topic.front.dao.TopicLifeAdviceFrontDao;
import com.cenmw.topic.po.TopicLifeAdvice;
import com.cenmw.util.PageBean;

public class TopicLifeAdviceFrontService {
	private TopicLifeAdviceFrontDao topicLifeAdviceFrontDao;

	public PageBean findTopicLifeAdviceHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicLifeAdviceFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveTopicLifeAdvice(TopicLifeAdvice topicLifeAdvice) {
		boolean status = true;
		topicLifeAdviceFrontDao.saveTopicLifeAdvice(topicLifeAdvice);
		topicLifeAdviceFrontDao.updateTopicLifeAdvice(topicLifeAdvice);
		return status;
	}

	public List findTopicLifeAdviceInList(int top) {
		String hql = "from TopicLifeAdvice order by number desc , id asc";
		return topicLifeAdviceFrontDao.getListForHql(hql, null,top);
	}
	
	public TopicLifeAdvice getTopicLifeAdvice(Integer tid, Integer ttaid) {
		String hql = "from TopicLifeAdvice where tid=" + tid + " and ttaid="
				+ ttaid;
		List list = topicLifeAdviceFrontDao.getListForHql(hql, null);
		TopicLifeAdvice topicLifeAdvice = null;
		if (list != null && !list.isEmpty()) {
			topicLifeAdvice = (TopicLifeAdvice) list.get(0);
		}
		return topicLifeAdvice;
	}

	public boolean updateTopicLifeAdvice(TopicLifeAdvice topicLifeAdvice) {
		boolean status = true;
		topicLifeAdviceFrontDao.updateTopicLifeAdvice(topicLifeAdvice);
		return status;
	}

	public TopicLifeAdvice getTopicLifeAdvice(int id) {
		return topicLifeAdviceFrontDao.getTopicLifeAdviceById(id);
	}

	public TopicLifeAdviceFrontDao getTopicLifeAdviceFrontDao() {
		return topicLifeAdviceFrontDao;
	}

	public void setTopicLifeAdviceFrontDao(TopicLifeAdviceFrontDao topicLifeAdviceFrontDao) {
		this.topicLifeAdviceFrontDao = topicLifeAdviceFrontDao;
	}

}
