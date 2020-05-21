package com.cenmw.topic.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.topic.manager.dao.TopicInfoManagerDao;
import com.cenmw.topic.po.TopicInfo;
import com.cenmw.util.PageBean;

public class TopicInfoManagerService {
	private TopicInfoManagerDao topicInfoManagerDao;

	public PageBean findTopicInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicInfoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveTopicInfo(TopicInfo topicInfo) {
		boolean status = true;
		topicInfoManagerDao.saveTopicInfo(topicInfo);
		topicInfoManagerDao.updateTopicInfo(topicInfo);
		return status;
	}

	public List findTopicInfoInList(int type) {
		String hql = "from TopicInfo where isdel=0 and type=" + type
				+ " order by id";
		return topicInfoManagerDao.getListForHql(hql, null);
	}

	public boolean updateTopicInfo(TopicInfo topicInfo) {
		boolean status = true;
		topicInfoManagerDao.updateTopicInfo(topicInfo);
		return status;
	}

	public TopicInfo getTopicInfo(int id) {
		return topicInfoManagerDao.getTopicInfoById(id);
	}

	public void deleteTopicInfoById(int id) {
		TopicInfo pb = getTopicInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			topicInfoManagerDao.saveTopicInfo(pb);
		}
	}

	public void deleteTopicInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteTopicInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public TopicInfoManagerDao getTopicInfoManagerDao() {
		return topicInfoManagerDao;
	}

	public void setTopicInfoManagerDao(TopicInfoManagerDao topicInfoManagerDao) {
		this.topicInfoManagerDao = topicInfoManagerDao;
	}


}
