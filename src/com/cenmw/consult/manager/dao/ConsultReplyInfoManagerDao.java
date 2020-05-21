package com.cenmw.consult.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.consult.po.ConsultReplyInfo;

public class ConsultReplyInfoManagerDao extends BaseHibernateDao {

	public void saveConsultReplyInfo(ConsultReplyInfo consultReplyInfo) {
		save(consultReplyInfo);
	}

	public void deleteConsultReplyInfo(ConsultReplyInfo consultReplyInfo) {
		delete(consultReplyInfo);
	}

	public ConsultReplyInfo getConsultReplyInfoById(int id) {
		return (ConsultReplyInfo) findObjectById(ConsultReplyInfo.class, id);
	}

	public void updateConsultReplyInfo(ConsultReplyInfo consultReplyInfo) {
		updateObject(consultReplyInfo);
	}

}
