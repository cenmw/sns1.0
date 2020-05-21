package com.cenmw.topic.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.topic.manager.dao.TopicClassManagerDao;
import com.cenmw.topic.po.TopicClass;
import com.cenmw.util.PageBean;

public class TopicClassManagerService {
	private TopicClassManagerDao topicClassManagerDao;

	public PageBean findTopicClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicClassManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveTopicClass(TopicClass topicClass) {
		boolean status = true;
		topicClassManagerDao.saveTopicClass(topicClass);
		topicClassManagerDao.updateTopicClass(topicClass);
		return status;
	}

	public List findTopicClassInList() {
		String hql = "from TopicClass where isdel=0 order by id";
		return topicClassManagerDao.getListForHql(hql, null);
	}
	
	public boolean updateTopicClass(TopicClass topicClass) {
		boolean status = true;
		topicClassManagerDao.updateTopicClass(topicClass);
		return status;
	}

	public TopicClass getTopicClass(int id) {
		return topicClassManagerDao.getTopicClassById(id);
	}

	public void deleteTopicClassById(int id) {
		TopicClass pb = getTopicClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			topicClassManagerDao.saveTopicClass(pb);
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

	public TopicClassManagerDao getTopicClassManagerDao() {
		return topicClassManagerDao;
	}

	public void setTopicClassManagerDao(TopicClassManagerDao topicClassManagerDao) {
		this.topicClassManagerDao = topicClassManagerDao;
	}


}
