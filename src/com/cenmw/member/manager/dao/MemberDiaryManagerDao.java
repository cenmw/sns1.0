package com.cenmw.member.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberDiary;

public class MemberDiaryManagerDao extends BaseHibernateDao {

	public void saveMemberDiary(MemberDiary memberDiary) {
		save(memberDiary);
	}

	public void deleteMemberDiary(MemberDiary memberDiary) {
		delete(memberDiary);
	}

	public MemberDiary getMemberDiaryById(int id) {
		return (MemberDiary) findObjectById(MemberDiary.class, id);
	}

	public void updateMemberDiary(MemberDiary memberDiary) {
		updateObject(memberDiary);
	}

}
