package com.cenmw.store.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberStore;

public class MemberStoreFrontDao extends BaseHibernateDao {

	public void saveMemberStore(MemberStore memberStore) {
		save(memberStore);
	}

	public void deleteMemberStore(MemberStore memberStore) {
		delete(memberStore);
	}

	public MemberStore getMemberStoreById(int id) {
		return (MemberStore) findObjectById(MemberStore.class, id);
	}

	public void updateMemberStore(MemberStore memberStore) {
		updateObject(memberStore);
	}

}
