package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberMood;

public class MemberMoodCenterDao extends BaseHibernateDao {

	public void saveMemberMood(MemberMood memberMood) {
		save(memberMood);
	}

	public void deleteMemberMood(MemberMood memberMood) {
		delete(memberMood);
	}

	public MemberMood getMemberMoodById(int id) {
		return (MemberMood) findObjectById(MemberMood.class, id);
	}

	public void updateMemberMood(MemberMood memberMood) {
		updateObject(memberMood);
	}

}
