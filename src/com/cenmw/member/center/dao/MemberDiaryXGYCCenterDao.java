package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberDiaryXGYC;

public class MemberDiaryXGYCCenterDao extends BaseHibernateDao {

	public void saveMemberDiaryXGYC(MemberDiaryXGYC memberDiaryXGYC) {
		save(memberDiaryXGYC);
	}

	public void deleteMemberDiaryXGYC(MemberDiaryXGYC memberDiaryXGYC) {
		delete(memberDiaryXGYC);
	}

	public MemberDiaryXGYC getMemberDiaryXGYCById(int id) {
		return (MemberDiaryXGYC) findObjectById(MemberDiaryXGYC.class, id);
	}

	public void updateMemberDiaryXGYC(MemberDiaryXGYC memberDiaryXGYC) {
		updateObject(memberDiaryXGYC);
	}

}
