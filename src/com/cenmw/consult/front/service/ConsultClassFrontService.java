package com.cenmw.consult.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.consult.front.dao.ConsultClassFrontDao;
import com.cenmw.consult.po.ConsultClass;
import com.cenmw.util.PageBean;

public class ConsultClassFrontService {
	private ConsultClassFrontDao consultClassFrontDao;

	public PageBean findConsultClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return consultClassFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveConsultClass(ConsultClass consultClass) {
		boolean status = true;
		consultClassFrontDao.saveConsultClass(consultClass);
		consultClassFrontDao.updateConsultClass(consultClass);
		return status;
	}

	public List findConsultClassInList(int type) {
		String hql = "from ConsultClass where isdel=0 and type=" + type
				+ " order by id";
		return consultClassFrontDao.getListForHql(hql, null);
	}
	
	public boolean updateConsultClass(ConsultClass consultClass) {
		boolean status = true;
		consultClassFrontDao.updateConsultClass(consultClass);
		return status;
	}

	public ConsultClass getConsultClass(int id) {
		return consultClassFrontDao.getConsultClassById(id);
	}

	public void deleteConsultClassById(int id) {
		ConsultClass pb = getConsultClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			consultClassFrontDao.saveConsultClass(pb);
		}
	}

	public void deleteConsultClassByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteConsultClassById(new Integer(idsArr[i]));
			}
		}
	}

	public ConsultClassFrontDao getConsultClassFrontDao() {
		return consultClassFrontDao;
	}

	public void setConsultClassFrontDao(ConsultClassFrontDao consultClassFrontDao) {
		this.consultClassFrontDao = consultClassFrontDao;
	}


}
