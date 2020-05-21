package com.cenmw.member.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberBlogClass;

public class MemberBlogClassFrontDao extends BaseHibernateDao {

	public void saveMemberBlogClass(MemberBlogClass memberBlogClass) {
		save(memberBlogClass);
	}

	public void deleteMemberBlogClass(MemberBlogClass memberBlogClass) {
		delete(memberBlogClass);
	}

	public MemberBlogClass getMemberBlogClassById(int id) {
		return (MemberBlogClass) findObjectById(MemberBlogClass.class, id);
	}

	public void updateMemberBlogClass(MemberBlogClass memberBlogClass) {
		updateObject(memberBlogClass);
	}

}
