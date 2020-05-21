package com.cenmw.member.center.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.center.dao.MemberLogCenterDao;
import com.cenmw.member.po.MemberFriend;
import com.cenmw.member.po.MemberLog;
import com.cenmw.util.PageBean;

public class MemberLogCenterService {
	private MemberLogCenterDao memberLogCenterDao;

	public PageBean findMemberLogHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberLogCenterDao.findListHqlForPage(hql, orderstr, map, cpage,
				pageSize);
	}

	public boolean saveMemberLog(MemberLog memberLog) {
		boolean status = true;
		memberLogCenterDao.saveMemberLog(memberLog);
		return status;
	}

	public List findMemberLogInList(int mid) {
		String hql = "from MemberLog where isdel=0 and mid=" + mid
				+ " order by id";
		return memberLogCenterDao.getListForHql(hql, null);
	}

	public boolean updateMemberLog(MemberLog memberLog) {
		boolean status = true;
		memberLogCenterDao.updateMemberLog(memberLog);
		return status;
	}

	public MemberLog getMemberLog(int id) {
		return memberLogCenterDao.getMemberLogById(id);
	}

	public void deleteMemberLogById(int id) {
		MemberLog pb = getMemberLog(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberLogCenterDao.saveMemberLog(pb);
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

	public MemberLogCenterDao getMemberLogCenterDao() {
		return memberLogCenterDao;
	}

	public void setMemberLogCenterDao(MemberLogCenterDao memberLogCenterDao) {
		this.memberLogCenterDao = memberLogCenterDao;
	}

}
