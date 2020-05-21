package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberFriendClassManagerDao;
import com.cenmw.member.po.MemberFriendClass;
import com.cenmw.util.PageBean;

public class MemberFriendClassManagerService {
	private MemberFriendClassManagerDao memberFriendClassManagerDao;

	public PageBean findMemberFriendClassHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberFriendClassManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberFriendClass(MemberFriendClass memberFriendClass) {
		boolean status = true;
		memberFriendClassManagerDao.saveMemberFriendClass(memberFriendClass);
		memberFriendClassManagerDao.updateMemberFriendClass(memberFriendClass);
		return status;
	}

	public List findMemberFriendClassInList(int mid) {
		String hql = "from MemberFriendClass where isdel=0 and mid=" + mid
				+ " order by id";
		return memberFriendClassManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberFriendClass(MemberFriendClass memberFriendClass) {
		boolean status = true;
		memberFriendClassManagerDao.updateMemberFriendClass(memberFriendClass);
		return status;
	}

	public MemberFriendClass getMemberFriendClass(int id) {
		return memberFriendClassManagerDao.getMemberFriendClassById(id);
	}

	public void deleteMemberFriendClassById(int id) {
		MemberFriendClass pb = getMemberFriendClass(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberFriendClassManagerDao.saveMemberFriendClass(pb);
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

	public MemberFriendClassManagerDao getMemberFriendClassManagerDao() {
		return memberFriendClassManagerDao;
	}

	public void setMemberFriendClassManagerDao(MemberFriendClassManagerDao memberFriendClassManagerDao) {
		this.memberFriendClassManagerDao = memberFriendClassManagerDao;
	}


}
