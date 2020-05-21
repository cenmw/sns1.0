package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberStatus;

public class MemberStatusCenterDao extends BaseHibernateDao {

	public void saveMemberStatus(MemberStatus memberStatus) {
		save(memberStatus);
	}

	public void deleteMemberStatus(MemberStatus memberStatus) {
		delete(memberStatus);
	}

	public MemberStatus getMemberStatusById(int id) {
		return (MemberStatus) findObjectById(MemberStatus.class, id);
	}

	public void updateMemberStatus(MemberStatus memberStatus) {
		updateObject(memberStatus);
	}

}
