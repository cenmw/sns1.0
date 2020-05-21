package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberLLJL;

public class MemberLLJLCenterDao extends BaseHibernateDao {

	public void saveMemberLLJL(MemberLLJL memberLLJL) {
		save(memberLLJL);
	}

	public void deleteMemberLLJL(MemberLLJL memberLLJL) {
		delete(memberLLJL);
	}

	public MemberLLJL getMemberLLJLById(int id) {
		return (MemberLLJL) findObjectById(MemberLLJL.class, id);
	}

	public void updateMemberLLJL(MemberLLJL memberLLJL) {
		updateObject(memberLLJL);
	}

}
