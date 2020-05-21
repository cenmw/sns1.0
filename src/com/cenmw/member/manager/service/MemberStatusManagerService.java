package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberStatusManagerDao;
import com.cenmw.member.po.MemberStatus;
import com.cenmw.util.PageBean;

public class MemberStatusManagerService {
	private MemberStatusManagerDao memberStatusManagerDao;

	public PageBean findMemberStatusHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberStatusManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberStatus(MemberStatus memberStatus) {
		boolean status = true;
		memberStatusManagerDao.saveMemberStatus(memberStatus);
		memberStatusManagerDao.updateMemberStatus(memberStatus);
		return status;
	}

	public List findMemberStatusInList(int mid) {
		String hql = "from MemberStatus where isdel=0 and mid=" + mid
				+ " order by id";
		return memberStatusManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberStatus(MemberStatus memberStatus) {
		boolean status = true;
		memberStatusManagerDao.updateMemberStatus(memberStatus);
		return status;
	}

	public MemberStatus getMemberStatus(int id) {
		return memberStatusManagerDao.getMemberStatusById(id);
	}

	public void deleteMemberStatusById(int id) {
		MemberStatus pb = getMemberStatus(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberStatusManagerDao.saveMemberStatus(pb);
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

	public MemberStatusManagerDao getMemberStatusManagerDao() {
		return memberStatusManagerDao;
	}

	public void setMemberStatusManagerDao(MemberStatusManagerDao memberStatusManagerDao) {
		this.memberStatusManagerDao = memberStatusManagerDao;
	}


}
