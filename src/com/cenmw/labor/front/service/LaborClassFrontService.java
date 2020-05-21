package com.cenmw.labor.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.labor.front.dao.LaborClassFrontDao;
import com.cenmw.labor.po.LaborClass;
import com.cenmw.util.PageBean;

public class LaborClassFrontService {
	private LaborClassFrontDao laborClassFrontDao;

	public PageBean findLaborClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return laborClassFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLaborClass(LaborClass laborClass) {
		boolean status = true;
		laborClassFrontDao.saveLaborClass(laborClass);
		laborClassFrontDao.updateLaborClass(laborClass);
		return status;
	}

	public List findLaborClassInList() {
		String hql = "from LaborClass where isdel=0 order by id";
		return laborClassFrontDao.getListForHql(hql, null);
	}
	
	public boolean updateLaborClass(LaborClass laborClass) {
		boolean status = true;
		laborClassFrontDao.updateLaborClass(laborClass);
		return status;
	}

	public LaborClass getLaborClass(int id) {
		return laborClassFrontDao.getLaborClassById(id);
	}

	public void deleteLaborClassById(int id) {
		LaborClass pb = getLaborClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			laborClassFrontDao.saveLaborClass(pb);
		}
	}

	public void deleteLaborClassByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteLaborClassById(new Integer(idsArr[i]));
			}
		}
	}

	public LaborClassFrontDao getLaborClassFrontDao() {
		return laborClassFrontDao;
	}

	public void setLaborClassFrontDao(LaborClassFrontDao laborClassFrontDao) {
		this.laborClassFrontDao = laborClassFrontDao;
	}


}
