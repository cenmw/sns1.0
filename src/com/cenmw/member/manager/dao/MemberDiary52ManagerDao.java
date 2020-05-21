package com.cenmw.member.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberDiary52;

public class MemberDiary52ManagerDao extends BaseHibernateDao {

	public void saveMemberDiary52(MemberDiary52 memberDiary52) {
		save(memberDiary52);
	}

	public void deleteMemberDiary52(MemberDiary52 memberDiary52) {
		delete(memberDiary52);
	}

	public MemberDiary52 getMemberDiary52ById(int id) {
		return (MemberDiary52) findObjectById(MemberDiary52.class, id);
	}

	public void updateMemberDiary52(MemberDiary52 memberDiary52) {
		updateObject(memberDiary52);
	}

}
