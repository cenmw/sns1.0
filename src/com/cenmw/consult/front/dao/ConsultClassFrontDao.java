package com.cenmw.consult.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.consult.po.ConsultClass;

public class ConsultClassFrontDao extends BaseHibernateDao {

	public void saveConsultClass(ConsultClass consultClass) {
		save(consultClass);
	}

	public void deleteConsultClass(ConsultClass consultClass) {
		delete(consultClass);
	}

	public ConsultClass getConsultClassById(int id) {
		return (ConsultClass) findObjectById(ConsultClass.class, id);
	}

	public void updateConsultClass(ConsultClass consultClass) {
		updateObject(consultClass);
	}

}
