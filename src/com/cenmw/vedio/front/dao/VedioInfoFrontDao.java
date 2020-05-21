package com.cenmw.vedio.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.vedio.po.VedioInfo;

public class VedioInfoFrontDao extends BaseHibernateDao {

	public void saveVedioInfo(VedioInfo vedioInfo) {
		save(vedioInfo);
	}

	public void deleteVedioInfo(VedioInfo vedioInfo) {
		delete(vedioInfo);
	}

	public VedioInfo getVedioInfoById(int id) {
		return (VedioInfo) findObjectById(VedioInfo.class, id);
	}

	public void updateVedioInfo(VedioInfo vedioInfo) {
		updateObject(vedioInfo);
	}

}
