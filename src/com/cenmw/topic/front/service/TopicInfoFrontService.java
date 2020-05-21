package com.cenmw.topic.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.topic.front.dao.TopicInfoFrontDao;
import com.cenmw.topic.po.TopicInfo;
import com.cenmw.util.PageBean;

public class TopicInfoFrontService {
	private TopicInfoFrontDao topicInfoFrontDao;

	public PageBean findTopicInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicInfoFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveTopicInfo(TopicInfo topicInfo) {
		boolean status = true;
		topicInfoFrontDao.saveTopicInfo(topicInfo);
		topicInfoFrontDao.updateTopicInfo(topicInfo);
		return status;
	}

	public List findTopicInfoInList(int type) {
		String hql = "from TopicInfo where isdel=0 and type=" + type
				+ " order by id";
		return topicInfoFrontDao.getListForHql(hql, null);
	}

	public int getMemberTopicInListNumber(int cid) {
		String hql = "from TopicInfo where isdel=0 and rcid=" + cid;
		return topicInfoFrontDao.findAllRow(hql);
	}
	
	public boolean updateTopicInfo(TopicInfo topicInfo) {
		boolean status = true;
		topicInfoFrontDao.updateTopicInfo(topicInfo);
		return status;
	}

	public TopicInfo getTopicInfo(int id) {
		return topicInfoFrontDao.getTopicInfoById(id);
	}

	public void deleteTopicInfoById(int id) {
		TopicInfo pb = getTopicInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			topicInfoFrontDao.saveTopicInfo(pb);
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

	public TopicInfoFrontDao getTopicInfoFrontDao() {
		return topicInfoFrontDao;
	}

	public void setTopicInfoFrontDao(TopicInfoFrontDao topicInfoFrontDao) {
		this.topicInfoFrontDao = topicInfoFrontDao;
	}


}
