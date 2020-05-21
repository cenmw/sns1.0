package com.cenmw.consult.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.consult.manager.dao.ConsultInfoManagerDao;
import com.cenmw.consult.po.ConsultInfo;
import com.cenmw.util.PageBean;

public class ConsultInfoManagerService {
	private ConsultInfoManagerDao consultInfoManagerDao;

	public PageBean findConsultInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return consultInfoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveConsultInfo(ConsultInfo consultInfo) {
		boolean status = true;
		consultInfoManagerDao.saveConsultInfo(consultInfo);
		consultInfoManagerDao.updateConsultInfo(consultInfo);
		return status;
	}

	public List findConsultInfoInList(int cid) {
		String hql = "from ConsultInfo where isdel=0 and cid=" + cid
				+ " order by id";
		return consultInfoManagerDao.getListForHql(hql, null);
	}

	public boolean updateConsultInfo(ConsultInfo consultInfo) {
		boolean status = true;
		consultInfoManagerDao.updateConsultInfo(consultInfo);
		return status;
	}

	public ConsultInfo getConsultInfo(int id) {
		return consultInfoManagerDao.getConsultInfoById(id);
	}

	public void deleteConsultInfoById(int id) {
		ConsultInfo pb = getConsultInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			consultInfoManagerDao.saveConsultInfo(pb);
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

	public ConsultInfoManagerDao getConsultInfoManagerDao() {
		return consultInfoManagerDao;
	}

	public void setConsultInfoManagerDao(ConsultInfoManagerDao consultInfoManagerDao) {
		this.consultInfoManagerDao = consultInfoManagerDao;
	}


}
