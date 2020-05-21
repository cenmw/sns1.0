package com.cenmw.member.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberPhotoGroup;

public class MemberPhotoGroupFrontDao extends BaseHibernateDao {

	public void saveMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		save(memberPhotoGroup);
	}

	public void deleteMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		delete(memberPhotoGroup);
	}

	public MemberPhotoGroup getMemberPhotoGroupById(int id) {
		return (MemberPhotoGroup) findObjectById(MemberPhotoGroup.class, id);
	}

	public void updateMemberPhotoGroup(MemberPhotoGroup memberPhotoGroup) {
		updateObject(memberPhotoGroup);
	}

}
