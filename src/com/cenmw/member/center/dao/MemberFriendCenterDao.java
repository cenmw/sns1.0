package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberFriend;

public class MemberFriendCenterDao extends BaseHibernateDao {

	public void saveMemberFriend(MemberFriend memberFriend) {
		save(memberFriend);
	}

	public void deleteMemberFriend(MemberFriend memberFriend) {
		delete(memberFriend);
	}

	public MemberFriend getMemberFriendById(int id) {
		return (MemberFriend) findObjectById(MemberFriend.class, id);
	}

	public void updateMemberFriend(MemberFriend memberFriend) {
		updateObject(memberFriend);
	}

}
