package com.cenmw.member.center.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberPraise;

public class MemberPraiseCenterDao extends BaseHibernateDao {

	public void saveMemberPraise(MemberPraise memberPraise) {
		save(memberPraise);
	}

	public void deleteMemberPraise(MemberPraise memberPraise) {
		delete(memberPraise);
	}

	public MemberPraise getMemberPraiseById(int id) {
		return (MemberPraise) findObjectById(MemberPraise.class, id);
	}

	public void updateMemberPraise(MemberPraise memberPraise) {
		updateObject(memberPraise);
	}

}
