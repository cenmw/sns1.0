package com.cenmw.consult.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.consult.po.ConsultInfo;

public class ConsultInfoManagerDao extends BaseHibernateDao {

	public void saveConsultInfo(ConsultInfo consultInfo) {
		save(consultInfo);
	}

	public void deleteConsultInfo(ConsultInfo consultInfo) {
		delete(consultInfo);
	}

	public ConsultInfo getConsultInfoById(int id) {
		return (ConsultInfo) findObjectById(ConsultInfo.class, id);
	}

	public void updateConsultInfo(ConsultInfo consultInfo) {
		updateObject(consultInfo);
	}

}
