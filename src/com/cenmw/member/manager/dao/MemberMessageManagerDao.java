package com.cenmw.member.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberMessage;

public class MemberMessageManagerDao extends BaseHibernateDao {

	public void saveMemberMessage(MemberMessage memberMessage) {
		save(memberMessage);
	}

	public void deleteMemberMessage(MemberMessage memberMessage) {
		delete(memberMessage);
	}

	public MemberMessage getMemberMessageById(int id) {
		return (MemberMessage) findObjectById(MemberMessage.class, id);
	}

	public void updateMemberMessage(MemberMessage memberMessage) {
		updateObject(memberMessage);
	}

}
