package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberLogManagerDao;
import com.cenmw.member.po.MemberLog;
import com.cenmw.util.PageBean;

public class MemberLogManagerService {
	private MemberLogManagerDao memberLogManagerDao;

	public PageBean findMemberLogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberLogManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberLog(MemberLog memberLog) {
		boolean status = true;
		memberLogManagerDao.saveMemberLog(memberLog);
		memberLogManagerDao.updateMemberLog(memberLog);
		return status;
	}

	public List findMemberLogInList(int mid) {
		String hql = "from MemberLog where isdel=0 and mid=" + mid
				+ " order by id";
		return memberLogManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberLog(MemberLog memberLog) {
		boolean status = true;
		memberLogManagerDao.updateMemberLog(memberLog);
		return status;
	}

	public MemberLog getMemberLog(int id) {
		return memberLogManagerDao.getMemberLogById(id);
	}

	public void deleteMemberLogById(int id) {
		MemberLog pb = getMemberLog(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberLogManagerDao.saveMemberLog(pb);
		}
	}

	public void deleteMemberLogByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberLogById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberLogManagerDao getMemberLogManagerDao() {
		return memberLogManagerDao;
	}

	public void setMemberLogManagerDao(MemberLogManagerDao memberLogManagerDao) {
		this.memberLogManagerDao = memberLogManagerDao;
	}


}
