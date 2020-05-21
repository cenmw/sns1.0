package com.cenmw.vedio.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.vedio.front.dao.VedioClassFrontDao;
import com.cenmw.vedio.po.VedioClass;
import com.cenmw.util.PageBean;

public class VedioClassFrontService {
	private VedioClassFrontDao vedioClassFrontDao;

	public PageBean findVedioClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return vedioClassFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveVedioClass(VedioClass vedioClass) {
		boolean status = true;
		vedioClassFrontDao.saveVedioClass(vedioClass);
		vedioClassFrontDao.updateVedioClass(vedioClass);
		return status;
	}

	public List findVedioClassInList(int type) {
		String hql = "from VedioClass where isdel=0 and type=" + type
				+ " order by id";
		return vedioClassFrontDao.getListForHql(hql, null);
	}
	
	public List findVedioClassInList(int mid, int type) {
		String hql = "from VedioClass where isdel=0 and (mid=" + mid
				+ " or mid=0) and type=" + type + " order by mid";
		return vedioClassFrontDao.getListForHql(hql, null);
	}
	
	public boolean updateVedioClass(VedioClass vedioClass) {
		boolean status = true;
		vedioClassFrontDao.updateVedioClass(vedioClass);
		return status;
	}

	public VedioClass getVedioClass(int id) {
		return vedioClassFrontDao.getVedioClassById(id);
	}

	public void deleteVedioClassById(int id) {
		VedioClass pb = getVedioClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			vedioClassFrontDao.saveVedioClass(pb);
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

	public VedioClassFrontDao getVedioClassFrontDao() {
		return vedioClassFrontDao;
	}

	public void setVedioClassFrontDao(VedioClassFrontDao vedioClassFrontDao) {
		this.vedioClassFrontDao = vedioClassFrontDao;
	}


}
