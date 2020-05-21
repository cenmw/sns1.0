package com.cenmw.vedio.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.vedio.front.dao.VedioInfoFrontDao;
import com.cenmw.vedio.po.VedioInfo;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.util.PageBean;

public class VedioInfoFrontService {
	private VedioInfoFrontDao vedioInfoFrontDao;

	public PageBean findVedioInfoHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return vedioInfoFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveVedioInfo(VedioInfo vedioInfo) {
		boolean status = true;
		vedioInfoFrontDao.saveVedioInfo(vedioInfo);
		vedioInfoFrontDao.updateVedioInfo(vedioInfo);
		return status;
	}

	public List findVedioInfoInList(int cid) {
		String hql = "from VedioInfo where isdel=0 and cid=" + cid
				+ " order by id";
		return vedioInfoFrontDao.getListForHql(hql, null);
	}

	public int getMemberVedioInListNumber(int cid) {
		String hql = "from VedioInfo where isdel=0 and rcid=" + cid;
		return vedioInfoFrontDao.findAllRow(hql);
	}
	
	public VedioInfo getVedioInfo(int mid, int cid) {
		VedioInfo mp = null;
		String hql = "from VedioInfo where isdel=0 and mid=" + mid
				+ " and rcid=" + cid + " order by id";
		List list = vedioInfoFrontDao.getListForHql(hql, null);
		if (list != null && !list.isEmpty()) {
			mp = (VedioInfo) list.get(0);
		}
		return mp;
	}
	
	public boolean updateVedioInfo(VedioInfo vedioInfo) {
		boolean status = true;
		vedioInfoFrontDao.updateVedioInfo(vedioInfo);
		return status;
	}

	public VedioInfo getVedioInfo(int id) {
		return vedioInfoFrontDao.getVedioInfoById(id);
	}

	public void deleteVedioInfoById(int id) {
		VedioInfo pb = getVedioInfo(id);
		if (pb != null) {
			pb.setIsdel(1);
			vedioInfoFrontDao.saveVedioInfo(pb);
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

	public VedioInfoFrontDao getVedioInfoFrontDao() {
		return vedioInfoFrontDao;
	}

	public void setVedioInfoFrontDao(VedioInfoFrontDao vedioInfoFrontDao) {
		this.vedioInfoFrontDao = vedioInfoFrontDao;
	}


}
