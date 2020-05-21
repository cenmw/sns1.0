package com.cenmw.labor.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.labor.manager.dao.LaborClassManagerDao;
import com.cenmw.labor.po.LaborClass;
import com.cenmw.util.PageBean;

public class LaborClassManagerService {
	private LaborClassManagerDao laborClassManagerDao;

	public PageBean findLaborClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return laborClassManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveLaborClass(LaborClass laborClass) {
		boolean status = true;
		laborClassManagerDao.saveLaborClass(laborClass);
		laborClassManagerDao.updateLaborClass(laborClass);
		return status;
	}

	public List findLaborClassInList() {
		String hql = "from LaborClass where isdel=0 order by id";
		return laborClassManagerDao.getListForHql(hql, null);
	}
	
	public boolean updateLaborClass(LaborClass laborClass) {
		boolean status = true;
		laborClassManagerDao.updateLaborClass(laborClass);
		return status;
	}

	public LaborClass getLaborClass(int id) {
		return laborClassManagerDao.getLaborClassById(id);
	}

	public void deleteLaborClassById(int id) {
		LaborClass pb = getLaborClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			laborClassManagerDao.saveLaborClass(pb);
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

	public LaborClassManagerDao getLaborClassManagerDao() {
		return laborClassManagerDao;
	}

	public void setLaborClassManagerDao(LaborClassManagerDao laborClassManagerDao) {
		this.laborClassManagerDao = laborClassManagerDao;
	}


}
