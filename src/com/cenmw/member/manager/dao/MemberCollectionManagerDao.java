package com.cenmw.member.manager.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberCollection;

public class MemberCollectionManagerDao extends BaseHibernateDao {

	public void saveMemberCollection(MemberCollection memberCollection) {
		save(memberCollection);
	}

	public void deleteMemberCollection(MemberCollection memberCollection) {
		delete(memberCollection);
	}

	public MemberCollection getMemberCollectionById(int id) {
		return (MemberCollection) findObjectById(MemberCollection.class, id);
	}

	public void updateMemberCollection(MemberCollection memberCollection) {
		updateObject(memberCollection);
	}

}
