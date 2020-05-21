package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberDiary52ManagerDao;
import com.cenmw.member.po.MemberDiary52;
import com.cenmw.util.PageBean;

public class MemberDiary52ManagerService {
	private MemberDiary52ManagerDao memberDiary52ManagerDao;

	public PageBean findMemberDiary52HQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberDiary52ManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberDiary52(MemberDiary52 memberDiary52) {
		boolean status = true;
		memberDiary52ManagerDao.saveMemberDiary52(memberDiary52);
		memberDiary52ManagerDao.updateMemberDiary52(memberDiary52);
		return status;
	}

	public List findMemberDiary52InList(int mid) {
		String hql = "from MemberDiary52 where isdel=0 and mid=" + mid
				+ " order by id";
		return memberDiary52ManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberDiary52(MemberDiary52 memberDiary52) {
		boolean status = true;
		memberDiary52ManagerDao.updateMemberDiary52(memberDiary52);
		return status;
	}

	public MemberDiary52 getMemberDiary52(int id) {
		return memberDiary52ManagerDao.getMemberDiary52ById(id);
	}

	public void deleteMemberDiary52ById(int id) {
		MemberDiary52 pb = getMemberDiary52(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberDiary52ManagerDao.saveMemberDiary52(pb);
		}
	}

	public void deleteMemberDiary52ByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberDiary52ById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberDiary52ManagerDao getMemberDiary52ManagerDao() {
		return memberDiary52ManagerDao;
	}

	public void setMemberDiary52ManagerDao(MemberDiary52ManagerDao memberDiary52ManagerDao) {
		this.memberDiary52ManagerDao = memberDiary52ManagerDao;
	}


}
