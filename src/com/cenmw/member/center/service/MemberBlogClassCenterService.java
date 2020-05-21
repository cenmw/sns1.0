package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;
import java.util.Date;

import com.cenmw.member.center.dao.MemberBlogClassCenterDao;
import com.cenmw.member.po.MemberBlogClass;
import com.cenmw.util.PageBean;

public class MemberBlogClassCenterService {
	private MemberBlogClassCenterDao memberBlogClassCenterDao;

	public PageBean findMemberBlogClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberBlogClassCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberBlogClass(MemberBlogClass memberBlogClass) {
		boolean status = true;
		memberBlogClassCenterDao.saveMemberBlogClass(memberBlogClass);
		memberBlogClassCenterDao.updateMemberBlogClass(memberBlogClass);
		return status;
	}

	public List findMemberBlogClassInList(int mid, int type) {
		String hql = "from MemberBlogClass where isdel=0 and (mid=" + mid
				+ " or mid=0) and type=" + type + " order by mid";
		return memberBlogClassCenterDao.getListForHql(hql, null);
	}

	public MemberBlogClass getMemberBlogClass(int mid, int type, String title) {
		MemberBlogClass mbc = null;
		String hql = "from MemberBlogClass where isdel=0 and mid=" + mid
				+ " and type=" + type + " and title ='" + title
				+ "' order by mid";
		List list = memberBlogClassCenterDao.getListForHql(hql, null, 1);
		if (list != null && list.size() == 1) {
			mbc = (MemberBlogClass) list.get(0);
		} else {
			mbc = new MemberBlogClass();
			mbc.setType(type);
			mbc.setMid(mid);
			mbc.setTitle(title);
			mbc.setIsdel(new Integer(0));
			mbc.setSort(new Integer(0));
			mbc.setCtime(new Date());
			memberBlogClassCenterDao.save(mbc);
		}
		return mbc;
	}

	public boolean updateMemberBlogClass(MemberBlogClass memberBlogClass) {
		boolean status = true;
		memberBlogClassCenterDao.updateMemberBlogClass(memberBlogClass);
		return status;
	}

	public MemberBlogClass getMemberBlogClass(int id) {
		return memberBlogClassCenterDao.getMemberBlogClassById(id);
	}

	public void deleteMemberBlogClassById(int id) {
		MemberBlogClass pb = getMemberBlogClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberBlogClassCenterDao.saveMemberBlogClass(pb);
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

	public MemberBlogClassCenterDao getMemberBlogClassCenterDao() {
		return memberBlogClassCenterDao;
	}

	public void setMemberBlogClassCenterDao(
			MemberBlogClassCenterDao memberBlogClassCenterDao) {
		this.memberBlogClassCenterDao = memberBlogClassCenterDao;
	}

}
