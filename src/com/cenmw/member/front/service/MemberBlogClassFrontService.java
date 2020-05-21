package com.cenmw.member.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.front.dao.MemberBlogClassFrontDao;
import com.cenmw.member.po.MemberBlogClass;
import com.cenmw.util.PageBean;

public class MemberBlogClassFrontService {
	private MemberBlogClassFrontDao memberBlogClassFrontDao;

	public PageBean findMemberBlogClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberBlogClassFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberBlogClass(MemberBlogClass memberBlogClass) {
		boolean status = true;
		memberBlogClassFrontDao.saveMemberBlogClass(memberBlogClass);
		memberBlogClassFrontDao.updateMemberBlogClass(memberBlogClass);
		return status;
	}

	public List findMemberBlogClassInList(int mid) {
		String hql = "from MemberBlogClass where isdel=0 and mid=" + mid
				+ " order by id";
		return memberBlogClassFrontDao.getListForHql(hql, null);
	}

	public boolean updateMemberBlogClass(MemberBlogClass memberBlogClass) {
		boolean status = true;
		memberBlogClassFrontDao.updateMemberBlogClass(memberBlogClass);
		return status;
	}

	public MemberBlogClass getMemberBlogClass(int id) {
		return memberBlogClassFrontDao.getMemberBlogClassById(id);
	}

	public void deleteMemberBlogClassById(int id) {
		MemberBlogClass pb = getMemberBlogClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberBlogClassFrontDao.saveMemberBlogClass(pb);
		}
	}

	public void deleteMemberBlogClassByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberBlogClassById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberBlogClassFrontDao getMemberBlogClassFrontDao() {
		return memberBlogClassFrontDao;
	}

	public void setMemberBlogClassFrontDao(MemberBlogClassFrontDao memberBlogClassFrontDao) {
		this.memberBlogClassFrontDao = memberBlogClassFrontDao;
	}


}
