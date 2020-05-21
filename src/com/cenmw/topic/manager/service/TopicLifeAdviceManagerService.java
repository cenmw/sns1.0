package com.cenmw.topic.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.topic.manager.dao.TopicLifeAdviceManagerDao;
import com.cenmw.topic.po.TopicLifeAdvice;
import com.cenmw.util.PageBean;

public class TopicLifeAdviceManagerService {
	private TopicLifeAdviceManagerDao topicLifeAdviceManagerDao;

	public PageBean findTopicLifeAdviceHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicLifeAdviceManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveTopicLifeAdvice(TopicLifeAdvice topicLifeAdvice) {
		boolean status = true;
		topicLifeAdviceManagerDao.saveTopicLifeAdvice(topicLifeAdvice);
		topicLifeAdviceManagerDao.updateTopicLifeAdvice(topicLifeAdvice);
		return status;
	}

	public List findTopicLifeAdviceInList() {
		String hql = "from TopicLifeAdvice where isdel=0 order by sort desc , id asc";
		return topicLifeAdviceManagerDao.getListForHql(hql, null);
	}
	
	public boolean updateTopicLifeAdvice(TopicLifeAdvice topicLifeAdvice) {
		boolean status = true;
		topicLifeAdviceManagerDao.updateTopicLifeAdvice(topicLifeAdvice);
		return status;
	}

	public TopicLifeAdvice getTopicLifeAdvice(int id) {
		return topicLifeAdviceManagerDao.getTopicLifeAdviceById(id);
	}

	public void deleteTopicLifeAdviceById(int id) {
		TopicLifeAdvice pb = getTopicLifeAdvice(id);
		if (pb != null) {
			pb.setIsdel(1);
			topicLifeAdviceManagerDao.saveTopicLifeAdvice(pb);
		}
	}

	public void deleteTopicLifeAdviceByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteTopicLifeAdviceById(new Integer(idsArr[i]));
			}
		}
	}

	public TopicLifeAdviceManagerDao getTopicLifeAdviceManagerDao() {
		return topicLifeAdviceManagerDao;
	}

	public void setTopicLifeAdviceManagerDao(TopicLifeAdviceManagerDao topicLifeAdviceManagerDao) {
		this.topicLifeAdviceManagerDao = topicLifeAdviceManagerDao;
	}


}
