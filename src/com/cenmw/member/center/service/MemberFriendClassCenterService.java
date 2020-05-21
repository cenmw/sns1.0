package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberFriendClassCenterDao;
import com.cenmw.member.po.MemberFriendClass;
import com.cenmw.util.PageBean;

public class MemberFriendClassCenterService {
	private MemberFriendClassCenterDao memberFriendClassCenterDao;

	public PageBean findMemberFriendClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberFriendClassCenterDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberFriendClass(MemberFriendClass memberFriendClass) {
		boolean status = true;
		memberFriendClassCenterDao.saveMemberFriendClass(memberFriendClass);
		memberFriendClassCenterDao.updateMemberFriendClass(memberFriendClass);
		return status;
	}

	public List findMemberFriendClassInList(int mid) {
		String hql = "from MemberFriendClass where isdel=0 and mid=" + mid
				+ " order by id";
		return memberFriendClassCenterDao.getListForHql(hql, null);
	}

	public boolean updateMemberFriendClass(MemberFriendClass memberFriendClass) {
		boolean status = true;
		memberFriendClassCenterDao.updateMemberFriendClass(memberFriendClass);
		return status;
	}

	public MemberFriendClass getMemberFriendClass(int id) {
		return memberFriendClassCenterDao.getMemberFriendClassById(id);
	}

	public void deleteMemberFriendClassById(int id) {
		MemberFriendClass pb = getMemberFriendClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberFriendClassCenterDao.saveMemberFriendClass(pb);
		}
	}

	public void deleteMemberFriendClassByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberFriendClassById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberFriendClassCenterDao getMemberFriendClassCenterDao() {
		return memberFriendClassCenterDao;
	}

	public void setMemberFriendClassCenterDao(MemberFriendClassCenterDao memberFriendClassCenterDao) {
		this.memberFriendClassCenterDao = memberFriendClassCenterDao;
	}


}
