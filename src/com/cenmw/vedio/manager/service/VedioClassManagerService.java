package com.cenmw.vedio.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.vedio.manager.dao.VedioClassManagerDao;
import com.cenmw.vedio.po.VedioClass;
import com.cenmw.util.PageBean;

public class VedioClassManagerService {
	private VedioClassManagerDao vedioClassManagerDao;

	public PageBean findVedioClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return vedioClassManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveVedioClass(VedioClass vedioClass) {
		boolean status = true;
		vedioClassManagerDao.saveVedioClass(vedioClass);
		vedioClassManagerDao.updateVedioClass(vedioClass);
		return status;
	}

	public List findVedioClassInList(int type) {
		String hql = "from VedioClass where isdel=0 and type=" + type
				+ " order by id";
		return vedioClassManagerDao.getListForHql(hql, null);
	}
	
	public boolean updateVedioClass(VedioClass vedioClass) {
		boolean status = true;
		vedioClassManagerDao.updateVedioClass(vedioClass);
		return status;
	}

	public VedioClass getVedioClass(int id) {
		return vedioClassManagerDao.getVedioClassById(id);
	}

	public void deleteVedioClassById(int id) {
		VedioClass pb = getVedioClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			vedioClassManagerDao.saveVedioClass(pb);
		}
	}

	public void deleteVedioClassByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteVedioClassById(new Integer(idsArr[i]));
			}
		}
	}

	public VedioClassManagerDao getVedioClassManagerDao() {
		return vedioClassManagerDao;
	}

	public void setVedioClassManagerDao(VedioClassManagerDao vedioClassManagerDao) {
		this.vedioClassManagerDao = vedioClassManagerDao;
	}


}
