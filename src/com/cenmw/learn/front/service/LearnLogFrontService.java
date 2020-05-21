package com.cenmw.learn.front.service;

import java.util.List;
import java.util.Map;

import com.cenmw.learn.front.dao.LearnLogFrontDao;
import com.cenmw.learn.po.LearnLog;
import com.cenmw.util.PageBean;

public class LearnLogFrontService {
	private LearnLogFrontDao learnLogFrontDao;

	public PageBean findLearnLogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return learnLogFrontDao.findListHqlForPage(hql, orderstr, map, cpage,
				pageSize);
	}

	public boolean saveLearnLog(LearnLog learnLog) {
		boolean status = true;
		learnLogFrontDao.saveLearnLog(learnLog);
		learnLogFrontDao.updateLearnLog(learnLog);
		return status;
	}

	public int getMemberLearnLogListNumber(int lid) {
		String hql = "from LearnLog where isdel=0 and lid=" + lid;
		return learnLogFrontDao.findAllRow(hql);
	}

	public int getMemberLearnLogListNumberMid(int mid, int lid) {
		String hql = "from LearnLog where isdel=0 and mid=" + mid + " and lid="
				+ lid;
		return learnLogFrontDao.findAllRow(hql);
	}

	public int getMemberLearnLogListMaxcorrect(int lid) {
		String hql = "from LearnLog where isdel=0 and lid=" + lid
				+ " order by correct desc";
		List list = learnLogFrontDao.findList(hql);
		int maxcorrect = 0;
		if (list != null && !list.isEmpty()) {
			LearnLog ll = (LearnLog) list.get(0);
			maxcorrect = ll.getCorrect();
		}
		return maxcorrect;
	}

	public int getMemberLearnLogListMaxcorrectMid(int mid, int lid) {
		String hql = "from LearnLog where isdel=0 and mid=" + mid + " and lid="
				+ lid + " order by correct desc";
		List list = learnLogFrontDao.getListForHql(hql, null);
		int maxcorrect = 0;
		if (list != null && !list.isEmpty()) {
			LearnLog ll = (LearnLog) list.get(0);
			maxcorrect = ll.getCorrect();
		}
		return maxcorrect;
	}

	public boolean updateLearnLog(LearnLog learnLog) {
		boolean status = true;
		learnLogFrontDao.updateLearnLog(learnLog);
		return status;
	}

	public LearnLog getLearnLog(int id) {
		return learnLogFrontDao.getLearnLogById(id);
	}

	public void deleteLearnLogById(int id) {
		LearnLog pb = getLearnLog(id);
		if (pb != null) {
			pb.setIsdel(1);
			learnLogFrontDao.saveLearnLog(pb);
		}
	}

	public void deleteLearnLogByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteLearnLogById(new Integer(idsArr[i]));
			}
		}
	}

	public LearnLogFrontDao getLearnLogFrontDao() {
		return learnLogFrontDao;
	}

	public void setLearnLogFrontDao(LearnLogFrontDao learnLogFrontDao) {
		this.learnLogFrontDao = learnLogFrontDao;
	}

}
