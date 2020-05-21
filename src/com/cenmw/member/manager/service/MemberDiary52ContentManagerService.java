package com.cenmw.member.manager.service;

import java.util.Map;
import java.util.List;

import com.cenmw.member.manager.dao.MemberDiary52ContentManagerDao;
import com.cenmw.member.po.MemberDiary52Content;
import com.cenmw.util.PageBean;

public class MemberDiary52ContentManagerService {
	private MemberDiary52ContentManagerDao memberDiary52ContentManagerDao;

	public PageBean findMemberDiary52ContentHQLList(final String hql,
			final String orderstr, final Map map, final int cpage,
			final int pageSize) {
		return memberDiary52ContentManagerDao.findListHqlForPage(hql, orderstr, map,
				cpage, pageSize);
	}

	public boolean saveMemberDiary52Content(MemberDiary52Content memberDiary52Content) {
		boolean status = true;
		memberDiary52ContentManagerDao.saveMemberDiary52Content(memberDiary52Content);
		memberDiary52ContentManagerDao.updateMemberDiary52Content(memberDiary52Content);
		return status;
	}

	public List findMemberDiary52ContentInList(int day) {
		String hql = "from MemberDiary52Content where isdel=0 and zbegin>=" + day + " and zend<=" + day
				+ " order by sort, id";
		return memberDiary52ContentManagerDao.getListForHql(hql, null);
	}

	public boolean updateMemberDiary52Content(MemberDiary52Content memberDiary52Content) {
		boolean status = true;
		memberDiary52ContentManagerDao.updateMemberDiary52Content(memberDiary52Content);
		return status;
	}

	public MemberDiary52Content getMemberDiary52Content(int id) {
		return memberDiary52ContentManagerDao.getMemberDiary52ContentById(id);
	}

	public void deleteMemberDiary52ContentById(int id) {
		MemberDiary52Content pb = getMemberDiary52Content(id);
		if (pb != null) {
			pb.setIsdel(1);
			memberDiary52ContentManagerDao.saveMemberDiary52Content(pb);
		}
	}

	public void deleteMemberDiary52ContentByIds(String ids) {
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			if (!idsArr[i].equals("")) {
				deleteMemberDiary52ContentById(new Integer(idsArr[i]));
			}
		}
	}

	public MemberDiary52ContentManagerDao getMemberDiary52ContentManagerDao() {
		return memberDiary52ContentManagerDao;
	}

	public void setMemberDiary52ContentManagerDao(MemberDiary52ContentManagerDao memberDiary52ContentManagerDao) {
		this.memberDiary52ContentManagerDao = memberDiary52ContentManagerDao;
	}


}
