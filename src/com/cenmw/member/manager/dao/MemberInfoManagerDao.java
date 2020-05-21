package com.cenmw.member.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberInfo;

public class MemberInfoManagerDao extends BaseHibernateDao {

	public void saveMemberInfo(MemberInfo memberInfo) {
		save(memberInfo);
	}

	public void deleteMemberInfo(MemberInfo memberInfo) {
		delete(memberInfo);
	}

	public MemberInfo getMemberInfoById(int id) {
		return (MemberInfo) findObjectById(MemberInfo.class, id);
	}

	public void updateMemberInfo(MemberInfo memberInfo) {
		updateObject(memberInfo);
	}

}
