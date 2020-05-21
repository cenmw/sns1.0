package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberBlogManagerDao;
import com.cenmw.member.po.MemberBlog;
import com.cenmw.util.PageBean;

public class MemberBlogManagerService {
	private MemberBlogManagerDao memberBlogManagerDao;

	public PageBean findMemberBlogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberBlogManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberBlog(MemberBlog memberBlog) {
		boolean status = true;
		memberBlogManagerDao.saveMemberBlog(memberBlog);
		memberBlogManagerDao.updateMemberBlog(memberBlog);
		return status;
	}

	public List findMemberBlogInList(int mid) {
		String hql = "from MemberBlog where isdel=0 and mid=" + mid
				+ " order by id";
		return memberBlogManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberBlog(MemberBlog memberBlog) {
		boolean status = true;
		memberBlogManagerDao.updateMemberBlog(memberBlog);
		return status;
	}

	public MemberBlog getMemberBlog(int id) {
		return memberBlogManagerDao.getMemberBlogById(id);
	}

	public void deleteMemberBlogById(int id) {
		MemberBlog pb = getMemberBlog(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberBlogManagerDao.saveMemberBlog(pb);
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

	public MemberBlogManagerDao getMemberBlogManagerDao() {
		return memberBlogManagerDao;
	}

	public void setMemberBlogManagerDao(MemberBlogManagerDao memberBlogManagerDao) {
		this.memberBlogManagerDao = memberBlogManagerDao;
	}


}
