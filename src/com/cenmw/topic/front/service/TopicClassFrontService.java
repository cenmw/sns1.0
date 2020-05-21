package com.cenmw.topic.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.topic.front.dao.TopicClassFrontDao;
import com.cenmw.topic.po.TopicClass;
import com.cenmw.util.PageBean;

public class TopicClassFrontService {
	private TopicClassFrontDao topicClassFrontDao;

	public PageBean findTopicClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicClassFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveTopicClass(TopicClass topicClass) {
		boolean status = true;
		topicClassFrontDao.saveTopicClass(topicClass);
		topicClassFrontDao.updateTopicClass(topicClass);
		return status;
	}

	public List findTopicClassInList() {
		String hql = "from TopicClass where isdel=0 order by id";
		return topicClassFrontDao.getListForHql(hql, null);
	}
	
	public boolean updateTopicClass(TopicClass topicClass) {
		boolean status = true;
		topicClassFrontDao.updateTopicClass(topicClass);
		return status;
	}

	public TopicClass getTopicClass(int id) {
		return topicClassFrontDao.getTopicClassById(id);
	}

	public void deleteTopicClassById(int id) {
		TopicClass pb = getTopicClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			topicClassFrontDao.saveTopicClass(pb);
		}
	}

	public void deleteTopicClassByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteTopicClassById(new Integer(idsArr[i]));
			}
		}
	}

	public TopicClassFrontDao getTopicClassFrontDao() {
		return topicClassFrontDao;
	}

	public void setTopicClassFrontDao(TopicClassFrontDao topicClassFrontDao) {
		this.topicClassFrontDao = topicClassFrontDao;
	}


}
