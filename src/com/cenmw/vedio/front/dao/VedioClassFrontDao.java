package com.cenmw.vedio.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.vedio.po.VedioClass;

public class VedioClassFrontDao extends BaseHibernateDao {

	public void saveVedioClass(VedioClass vedioClass) {
		save(vedioClass);
	}

	public void deleteVedioClass(VedioClass vedioClass) {
		delete(vedioClass);
	}

	public VedioClass getVedioClassById(int id) {
		return (VedioClass) findObjectById(VedioClass.class, id);
	}

	public void updateVedioClass(VedioClass vedioClass) {
		updateObject(vedioClass);
	}

}
