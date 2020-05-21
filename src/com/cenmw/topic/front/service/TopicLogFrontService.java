package com.cenmw.topic.front.service;

import java.util.List;
import java.util.Map;

import com.cenmw.topic.front.dao.TopicLogFrontDao;
import com.cenmw.topic.po.TopicLog;
import com.cenmw.util.PageBean;

public class TopicLogFrontService {
	private TopicLogFrontDao topicLogFrontDao;

	public PageBean findTopicLogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return topicLogFrontDao.findListHqlForPage(hql, orderstr, map, cpage,
				pageSize);
	}

	public boolean saveTopicLog(TopicLog topicLog) {
		boolean status = true;
		topicLogFrontDao.saveTopicLog(topicLog);
		topicLogFrontDao.updateTopicLog(topicLog);
		return status;
	}

	public int getMemberTopicLogListNumber(int tid) {
		String hql = "from TopicLog where isdel=0 and tid=" + tid;
		return topicLogFrontDao.findAllRow(hql);
	}

	public int getMemberTopicLogListNumberMid(int mid, int tid) {
		String hql = "from TopicLog where isdel=0 and mid=" + mid + " and tid="
				+ tid;
		return topicLogFrontDao.findAllRow(hql);
	}

	public int getMemberTopicLogListMaxcorrect(int tid) {
		String hql = "from TopicLog where isdel=0 and tid=" + tid
				+ " order by correct desc";
		List list = topicLogFrontDao.findList(hql);
		int maxcorrect = 0;
		if (list != null && !list.isEmpty()) {
			TopicLog ll = (TopicLog) list.get(0);
			maxcorrect = ll.getCorrect();
		}
		return maxcorrect;
	}

	public int getMemberTopicLogListMaxcorrectMid(int mid, int tid) {
		String hql = "from TopicLog where isdel=0 and mid=" + mid + " and tid="
				+ tid + " order by correct desc";
		List list = topicLogFrontDao.getListForHql(hql, null);
		int maxcorrect = 0;
		if (list != null && !list.isEmpty()) {
			TopicLog ll = (TopicLog) list.get(0);
			maxcorrect = ll.getCorrect();
		}
		return maxcorrect;
	}

	public boolean updateTopicLog(TopicLog topicLog) {
		boolean status = true;
		topicLogFrontDao.updateTopicLog(topicLog);
		return status;
	}

	public TopicLog getTopicLog(int id) {
		return topicLogFrontDao.getTopicLogById(id);
	}

	public void deleteTopicLogById(int id) {
		TopicLog pb = getTopicLog(id);
		if (pb != null) {
			pb.setIsdel(1);
			topicLogFrontDao.saveTopicLog(pb);
		}
	}

	public void deleteTopicLogByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteTopicLogById(new Integer(idsArr[i]));
			}
		}
	}

	public TopicLogFrontDao getTopicLogFrontDao() {
		return topicLogFrontDao;
	}

	public void setTopicLogFrontDao(TopicLogFrontDao topicLogFrontDao) {
		this.topicLogFrontDao = topicLogFrontDao;
	}

}
