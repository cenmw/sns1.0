package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberLog;

public class MemberLogCenterDao extends BaseHibernateDao {

	public void saveMemberLog(MemberLog memberLog) {
		save(memberLog);
	}

	public void deleteMemberLog(MemberLog memberLog) {
		delete(memberLog);
	}

	public MemberLog getMemberLogById(int id) {
		return (MemberLog) findObjectById(MemberLog.class, id);
	}

	public void updateMemberLog(MemberLog memberLog) {
		updateObject(memberLog);
	}

}
