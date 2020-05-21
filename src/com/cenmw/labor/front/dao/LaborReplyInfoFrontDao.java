package com.cenmw.labor.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.labor.po.LaborReplyInfo;

public class LaborReplyInfoFrontDao extends BaseHibernateDao {

	public void saveLaborReplyInfo(LaborReplyInfo laborReplyInfo) {
		save(laborReplyInfo);
	}

	public void deleteLaborReplyInfo(LaborReplyInfo laborReplyInfo) {
		delete(laborReplyInfo);
	}

	public LaborReplyInfo getLaborReplyInfoById(int id) {
		return (LaborReplyInfo) findObjectById(LaborReplyInfo.class, id);
	}

	public void updateLaborReplyInfo(LaborReplyInfo laborReplyInfo) {
		updateObject(laborReplyInfo);
	}

}
