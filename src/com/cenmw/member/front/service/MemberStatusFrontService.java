package com.cenmw.member.front.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.front.dao.MemberStatusFrontDao;
import com.cenmw.member.po.MemberStatus;
import com.cenmw.util.PageBean;

public class MemberStatusFrontService {
	private MemberStatusFrontDao memberStatusFrontDao;

	public PageBean findMemberStatusHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberStatusFrontDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberStatus(MemberStatus memberStatus) {
		boolean status = true;
		memberStatusFrontDao.saveMemberStatus(memberStatus);
		memberStatusFrontDao.updateMemberStatus(memberStatus);
		return status;
	}

	public List findMemberStatusInList(int mid) {
		String hql = "from MemberStatus where isdel=0 and mid=" + mid
				+ " order by id";
		return memberStatusFrontDao.getListForHql(hql, null);
	}

	public boolean updateMemberStatus(MemberStatus memberStatus) {
		boolean status = true;
		memberStatusFrontDao.updateMemberStatus(memberStatus);
		return status;
	}

	public MemberStatus getMemberStatus(int id) {
		return memberStatusFrontDao.getMemberStatusById(id);
	}

	public void deleteMemberStatusById(int id) {
		MemberStatus pb = getMemberStatus(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberStatusFrontDao.saveMemberStatus(pb);
		}
	}

	public void deleteMemberStatusByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberStatusById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberStatusFrontDao getMemberStatusFrontDao() {
		return memberStatusFrontDao;
	}

	public void setMemberStatusFrontDao(MemberStatusFrontDao memberStatusFrontDao) {
		this.memberStatusFrontDao = memberStatusFrontDao;
	}


}
