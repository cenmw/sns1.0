package com.cenmw.vedio.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.vedio.manager.dao.VedioInfoManagerDao;
import com.cenmw.vedio.po.VedioInfo;
import com.cenmw.util.PageBean;

public class VedioInfoManagerService {
	private VedioInfoManagerDao vedioInfoManagerDao;

	public PageBean findVedioInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return vedioInfoManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveVedioInfo(VedioInfo vedioInfo) {
		boolean status = true;
		vedioInfoManagerDao.saveVedioInfo(vedioInfo);
		vedioInfoManagerDao.updateVedioInfo(vedioInfo);
		return status;
	}

	public List findVedioInfoInList(int cid) {
		String hql = "from VedioInfo where isdel=0 and cid=" + cid
				+ " order by id";
		return vedioInfoManagerDao.getListForHql(hql, null);
	}

	public boolean updateVedioInfo(VedioInfo vedioInfo) {
		boolean status = true;
		vedioInfoManagerDao.updateVedioInfo(vedioInfo);
		return status;
	}

	public VedioInfo getVedioInfo(int id) {
		return vedioInfoManagerDao.getVedioInfoById(id);
	}

	public void deleteVedioInfoById(int id) {
		VedioInfo pb = getVedioInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			vedioInfoManagerDao.saveVedioInfo(pb);
		}
	}

	public void deleteVedioInfoByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteVedioInfoById(new Integer(idsArr[i]));
			}
		}
	}

	public VedioInfoManagerDao getVedioInfoManagerDao() {
		return vedioInfoManagerDao;
	}

	public void setVedioInfoManagerDao(VedioInfoManagerDao vedioInfoManagerDao) {
		this.vedioInfoManagerDao = vedioInfoManagerDao;
	}


}
