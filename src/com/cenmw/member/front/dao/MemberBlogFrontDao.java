package com.cenmw.member.front.dao;

import com.cenmw.base.BaseHibernateDao;
import com.cenmw.member.po.MemberBlog;

public class MemberBlogFrontDao extends BaseHibernateDao {

	public void saveMemberBlog(MemberBlog memberBlog) {
		save(memberBlog);
	}

	public void deleteMemberBlog(MemberBlog memberBlog) {
		delete(memberBlog);
	}

	public MemberBlog getMemberBlogById(int id) {
		return (MemberBlog) findObjectById(MemberBlog.class, id);
	}

	public void updateMemberBlog(MemberBlog memberBlog) {
		updateObject(memberBlog);
	}

}
