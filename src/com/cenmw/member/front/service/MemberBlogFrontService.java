package com.cenmw.member.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.front.dao.MemberBlogFrontDao;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.util.PageBean;

public class MemberBlogFrontService {
	private MemberBlogFrontDao memberBlogFrontDao;

	public PageBean findMemberBlogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberBlogFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberBlog(MemberBlog memberBlog) {
		boolean status = true;
		memberBlogFrontDao.saveMemberBlog(memberBlog);
		memberBlogFrontDao.updateMemberBlog(memberBlog);
		return status;
	}

	public List findMemberBlogInList(int mid) {
		String hql = "from MemberBlog where isdel=0 and mid=" + mid
				+ " order by id";
		return memberBlogFrontDao.getListForHql(hql, null);
	}

	public boolean updateMemberBlog(MemberBlog memberBlog) {
		boolean status = true;
		memberBlogFrontDao.updateMemberBlog(memberBlog);
		return status;
	}

	public MemberBlog getMemberBlog(int id) {
		return memberBlogFrontDao.getMemberBlogById(id);
	}

	public void deleteMemberBlogById(int id) {
		MemberBlog pb = getMemberBlog(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberBlogFrontDao.saveMemberBlog(pb);
		}
	}

	public void deleteMemberBlogByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberBlogById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberBlogFrontDao getMemberBlogFrontDao() {
		return memberBlogFrontDao;
	}

	public void setMemberBlogFrontDao(MemberBlogFrontDao memberBlogFrontDao) {
		this.memberBlogFrontDao = memberBlogFrontDao;
	}


}
