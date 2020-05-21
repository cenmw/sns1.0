package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberPraiseManagerDao;
import com.cenmw.member.po.MemberPraise;
import com.cenmw.util.PageBean;

public class MemberPraiseManagerService {
	private MemberPraiseManagerDao memberPraiseManagerDao;

	public PageBean findMemberPraiseHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberPraiseManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberPraise(MemberPraise memberPraise) {
		boolean status = true;
		memberPraiseManagerDao.saveMemberPraise(memberPraise);
		memberPraiseManagerDao.updateMemberPraise(memberPraise);
		return status;
	}

	public List findMemberPraiseInList(int mid) {
		String hql = "from MemberPraise where isdel=0 and mid=" + mid
				+ " order by id";
		return memberPraiseManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberPraise(MemberPraise memberPraise) {
		boolean status = true;
		memberPraiseManagerDao.updateMemberPraise(memberPraise);
		return status;
	}

	public MemberPraise getMemberPraise(int id) {
		return memberPraiseManagerDao.getMemberPraiseById(id);
	}

	public void deleteMemberPraiseById(int id) {
		MemberPraise pb = getMemberPraise(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberPraiseManagerDao.saveMemberPraise(pb);
		}
	}

	public void deleteMemberPraiseByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberPraiseById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberPraiseManagerDao getMemberPraiseManagerDao() {
		return memberPraiseManagerDao;
	}

	public void setMemberPraiseManagerDao(MemberPraiseManagerDao memberPraiseManagerDao) {
		this.memberPraiseManagerDao = memberPraiseManagerDao;
	}


}
