package com.cenmw.consult.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.consult.manager.dao.ConsultClassManagerDao;
import com.cenmw.consult.po.ConsultClass;
import com.cenmw.util.PageBean;

public class ConsultClassManagerService {
	private ConsultClassManagerDao consultClassManagerDao;

	public PageBean findConsultClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return consultClassManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveConsultClass(ConsultClass consultClass) {
		boolean status = true;
		consultClassManagerDao.saveConsultClass(consultClass);
		consultClassManagerDao.updateConsultClass(consultClass);
		return status;
	}

	public List findConsultClassInList(int type) {
		String hql = "from ConsultClass where isdel=0 and type=" + type
				+ " order by id";
		return consultClassManagerDao.getListForHql(hql, null);
	}
	
	public boolean updateConsultClass(ConsultClass consultClass) {
		boolean status = true;
		consultClassManagerDao.updateConsultClass(consultClass);
		return status;
	}

	public ConsultClass getConsultClass(int id) {
		return consultClassManagerDao.getConsultClassById(id);
	}

	public void deleteConsultClassById(int id) {
		ConsultClass pb = getConsultClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			consultClassManagerDao.saveConsultClass(pb);
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

	public ConsultClassManagerDao getConsultClassManagerDao() {
		return consultClassManagerDao;
	}

	public void setConsultClassManagerDao(ConsultClassManagerDao consultClassManagerDao) {
		this.consultClassManagerDao = consultClassManagerDao;
	}


}
