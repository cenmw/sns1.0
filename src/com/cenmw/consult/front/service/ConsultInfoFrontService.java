package com.cenmw.consult.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.consult.front.dao.ConsultInfoFrontDao;
import com.cenmw.consult.po.ConsultInfo;
import com.cenmw.util.PageBean;

public class ConsultInfoFrontService {
	private ConsultInfoFrontDao consultInfoFrontDao;

	public PageBean findConsultInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return consultInfoFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveConsultInfo(ConsultInfo consultInfo) {
		boolean status = true;
		consultInfoFrontDao.saveConsultInfo(consultInfo);
		consultInfoFrontDao.updateConsultInfo(consultInfo);
		return status;
	}

	public List findConsultInfoInList(int cid) {
		String hql = "from ConsultInfo where isdel=0 and cid=" + cid
				+ " order by id";
		return consultInfoFrontDao.getListForHql(hql, null);
	}

	public boolean updateConsultInfo(ConsultInfo consultInfo) {
		boolean status = true;
		consultInfoFrontDao.updateConsultInfo(consultInfo);
		return status;
	}

	public ConsultInfo getConsultInfo(int id) {
		return consultInfoFrontDao.getConsultInfoById(id);
	}

	public void deleteConsultInfoById(int id) {
		ConsultInfo pb = getConsultInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			consultInfoFrontDao.saveConsultInfo(pb);
		}
	}

	public void deleteConsultInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteConsultInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public ConsultInfoFrontDao getConsultInfoFrontDao() {
		return consultInfoFrontDao;
	}

	public void setConsultInfoFrontDao(ConsultInfoFrontDao consultInfoFrontDao) {
		this.consultInfoFrontDao = consultInfoFrontDao;
	}


}
