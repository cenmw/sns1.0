package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberFriendClass;

public class MemberFriendClassCenterDao extends BaseHibernateDao {

	public void saveMemberFriendClass(MemberFriendClass memberFriendClass) {
		save(memberFriendClass);
	}

	public void deleteMemberFriendClass(MemberFriendClass memberFriendClass) {
		delete(memberFriendClass);
	}

	public MemberFriendClass getMemberFriendClassById(int id) {
		return (MemberFriendClass) findObjectById(MemberFriendClass.class, id);
	}

	public void updateMemberFriendClass(MemberFriendClass memberFriendClass) {
		updateObject(memberFriendClass);
	}

}
