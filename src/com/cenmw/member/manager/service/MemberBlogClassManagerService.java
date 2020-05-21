package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberBlogClassManagerDao;
import com.cenmw.member.po.MemberBlogClass;
import com.cenmw.util.PageBean;

public class MemberBlogClassManagerService {
	private MemberBlogClassManagerDao memberBlogClassManagerDao;

	public PageBean findMemberBlogClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberBlogClassManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberBlogClass(MemberBlogClass memberBlogClass) {
		boolean status = true;
		memberBlogClassManagerDao.saveMemberBlogClass(memberBlogClass);
		memberBlogClassManagerDao.updateMemberBlogClass(memberBlogClass);
		return status;
	}

	public List findMemberBlogClassInList(int mid) {
		String hql = "from MemberBlogClass where isdel=0 and mid=" + mid
				+ " order by id";
		return memberBlogClassManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberBlogClass(MemberBlogClass memberBlogClass) {
		boolean status = true;
		memberBlogClassManagerDao.updateMemberBlogClass(memberBlogClass);
		return status;
	}

	public MemberBlogClass getMemberBlogClass(int id) {
		return memberBlogClassManagerDao.getMemberBlogClassById(id);
	}

	public void deleteMemberBlogClassById(int id) {
		MemberBlogClass pb = getMemberBlogClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberBlogClassManagerDao.saveMemberBlogClass(pb);
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

	public MemberBlogClassManagerDao getMemberBlogClassManagerDao() {
		return memberBlogClassManagerDao;
	}

	public void setMemberBlogClassManagerDao(MemberBlogClassManagerDao memberBlogClassManagerDao) {
		this.memberBlogClassManagerDao = memberBlogClassManagerDao;
	}


}
