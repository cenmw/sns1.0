package com.cenmw.member.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberPhoto;

public class MemberPhotoFrontDao extends BaseHibernateDao {

	public void saveMemberPhoto(MemberPhoto memberPhoto) {
		save(memberPhoto);
	}

	public void deleteMemberPhoto(MemberPhoto memberPhoto) {
		delete(memberPhoto);
	}

	public MemberPhoto getMemberPhotoById(int id) {
		return (MemberPhoto) findObjectById(MemberPhoto.class, id);
	}

	public void updateMemberPhoto(MemberPhoto memberPhoto) {
		updateObject(memberPhoto);
	}

}
