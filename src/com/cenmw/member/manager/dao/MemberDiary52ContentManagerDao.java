package com.cenmw.member.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberDiary52Content;

public class MemberDiary52ContentManagerDao extends BaseHibernateDao {

	public void saveMemberDiary52Content(MemberDiary52Content memberDiary52Content) {
		save(memberDiary52Content);
	}

	public void deleteMemberDiary52Content(MemberDiary52Content memberDiary52Content) {
		delete(memberDiary52Content);
	}

	public MemberDiary52Content getMemberDiary52ContentById(int id) {
		return (MemberDiary52Content) findObjectById(MemberDiary52Content.class, id);
	}

	public void updateMemberDiary52Content(MemberDiary52Content memberDiary52Content) {
		updateObject(memberDiary52Content);
	}

}
